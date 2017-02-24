package com.basic.xy.smartbulter.fragment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.BuildConfig;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.support.v4.os.ResultReceiver;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basic.xy.smartbulter.R;
import com.basic.xy.smartbulter.activity.LoginActivity;
import com.basic.xy.smartbulter.entity.MyUser;
import com.basic.xy.smartbulter.util.L;
import com.basic.xy.smartbulter.util.UtilToools;
import com.basic.xy.smartbulter.view.CustomDialog;

import java.io.File;
import java.io.IOException;
import java.security.Permission;
import java.security.Permissions;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.http.bean.Result;
import cn.bmob.v3.listener.UpdateListener;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {
    private View layout;
    private Button mLogoutBtn,mEditBtn,mCommitBtn;
    private EditText mNameET,mGenderET,mAgeET,mIntroET;
    private TextView mWuLiuTV,mGuiShuDiTV;
    private boolean isEdit = false;
    private String username,gender,age,intro;
    private CircleImageView mAvatar;
    private CustomDialog dialog;
    private Button mTakePhoto,mGallery,mCancel;

    public static final String AVATAR_NAME = "avatar.jpg";
    private File tempFile = null;
    public static final int REQUEST_TAKE_PHOTO = 101;
    public static final int REQUEST_GALLERY = 102;
    public static final int REQUEST_CROP = 103;
    private static int MY_PERMISSIONS_REQUEST_CAMERA = 1111;

    public UserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        UtilToools.requestPermission(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},getActivity(),MY_PERMISSIONS_REQUEST_CAMERA);
        layout = inflater.inflate(R.layout.fragment_user, container, false);
        initVariable();
        initView();
        return layout;
    }

    private void initVariable() {
        getUserInfo();
    }

    private void initView() {
        mLogoutBtn = (Button) layout.findViewById(R.id.btn_logout);
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出登录
                logOut();
            }
        });
        mEditBtn = (Button) layout.findViewById(R.id.btn_edit);
        mEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit == false) {
                    isEdit = true;
                    edit(true);
                } else {
                    isEdit = false;
                    edit(false);
                }
            }
        });
        mCommitBtn = (Button) layout.findViewById(R.id.btn_commit);
        mCommitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commit();
            }
        });
        mNameET = (EditText) layout.findViewById(R.id.et_name);
        mGenderET = (EditText) layout.findViewById(R.id.et_gender);
        mAgeET = (EditText) layout.findViewById(R.id.et_age2);
        mIntroET = (EditText) layout.findViewById(R.id.et_intro);
        mNameET.setEnabled(false);
        mGenderET.setEnabled(false);
        mAgeET.setEnabled(false);
        mIntroET.setEnabled(false);

        mAvatar = (CircleImageView) layout.findViewById(R.id.civ_avatar);
        mAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAvatar();
            }
        });
        setView();
    }

    private void onAvatar() {
        dialog = new CustomDialog(getActivity(), WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_avatar, R.style.pop_anim_style, Gravity.BOTTOM,0);

        mTakePhoto = (Button) dialog.findViewById(R.id.btn_take_photo);
        mTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTakePhoto();
            }
        });
        mGallery = (Button) dialog.findViewById(R.id.btn_gallery);
        mGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onGallery();
            }
        });
        mCancel = (Button) dialog.findViewById(R.id.btn_cancel_dialog);
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void setView() {
        //设置编辑框内容
        mNameET.setText(username);
        mAgeET.setText(age);
        mGenderET.setText(gender);
        mIntroET.setText(intro);
    }

    private void getUserInfo() {
        MyUser user = BmobUser.getCurrentUser(MyUser.class);
        username = user.getUsername();
        age = user.getAge()+"";
        gender = user.getGender();
        intro = user.getDesc();
    }

    private void logOut() {
        MyUser.logOut();
//        BmobUser currentUser = MyUser.getCurrentUser();
        Toast.makeText(getActivity(), "您已退出登录", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }

    private void commit() {
        MyUser user = new MyUser();
        username = mNameET.getText().toString().trim();
        gender = mGenderET.getText().toString().trim();
        age = mAgeET.getText().toString().trim();
        intro = mIntroET.getText().toString().trim();
        if (!(TextUtils.isEmpty(username) & TextUtils.isEmpty(gender) & TextUtils.isEmpty(age))) {
            user.setUsername(username);
            user.setGender(gender);
            user.setAge(Integer.parseInt(age));
            if (TextUtils.isEmpty(intro)) {
                user.setDesc("这个人很懒，什么都没有留下");
            } else {
                user.setDesc(intro);
            }
            BmobUser bmobUser = BmobUser.getCurrentUser();
            user.update(bmobUser.getObjectId(),new UpdateListener() {
                @Override
                public void done(BmobException e) {
                    if(e==null){
                        Toast.makeText(getActivity(), "更新用户信息成功", Toast.LENGTH_SHORT).show();
                        isEdit = false;
                        edit(false);
                        getUserInfo();
                        setView();
                    }else{
                        Toast.makeText(getActivity(), "更新用户信息失败", Toast.LENGTH_SHORT).show();
                        L.e(e.toString());
                    }
                }
            });
        }
    }

    /**
     * 编辑个人资料
     * @param isEdit
     */
    private void edit(boolean isEdit) {
        if (isEdit == true) {
            //编辑状态
            mEditBtn.setText(R.string.text_edit);
            mCommitBtn.setVisibility(View.VISIBLE);
            mNameET.setEnabled(true);
            mAgeET.setEnabled(true);
            mGenderET.setEnabled(true);
            mIntroET.setEnabled(true);
        } else {
            //改为不可编辑状态
            mEditBtn.setText("编辑资料");
            mCommitBtn.setVisibility(View.GONE);
            mNameET.setEnabled(false);
            mAgeET.setEnabled(false);
            mGenderET.setEnabled(false);
            mIntroET.setEnabled(false);
            setView();
        }
    }

    private void onGallery() {
        dialog.dismiss();
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,REQUEST_GALLERY);
    }

    private void onTakePhoto() {
        dialog.dismiss();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory(), AVATAR_NAME);
        file.mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Uri uri = FileProvider.getUriForFile(getActivity(), "com.basic.xy.smartbulter.fileprovider", file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent,REQUEST_TAKE_PHOTO);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_TAKE_PHOTO:
                    tempFile = new File(Environment.getExternalStorageDirectory(), AVATAR_NAME);
                    Uri uri = FileProvider.getUriForFile(getActivity(), "com.basic.xy.smartbulter.fileprovider", tempFile);
                    cropPhoto(uri);
                    break;
                case REQUEST_GALLERY:
                    cropPhoto(data.getData());
                    break;
                case REQUEST_CROP:

                    break;
            }
        } else {
            return;
        }
    }

    private void cropPhoto(Uri uri) {
        if (uri == null) {
            L.e("uri == null");
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //设置裁剪
        intent.putExtra("crop", "true");
        //裁剪宽高比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        //设置图片的质量
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        startActivityForResult(intent,REQUEST_CROP);
    }
}

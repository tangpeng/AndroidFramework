package com.onemore.goodproduct.mvpview;

/**
 * @author tangpeng
 *         mvc框架
 */
public interface MvpGetTokenView {

    public void fail(String message);

    public void Success(String path, String upload_token);//代表视频上传之后获取的token和key

    public void isUpload(int type);//1代表相册，2代表视频,3代表头像 4代表封面

    /**
     * 获取到的key是全路径
     *
     * @param type         0代表其他，1代表视频（获取视频的token，因为视频是获取视频的路径（断点续传））
     * @param path
     * @param upload_token
     */
    public void getAllKeyTokenSuccess(int type, String path, String upload_token);

}

package org.cs.dp.radar.api.entity.brs;

public class BssTaskProgram {
    private String task_id;
    private String status;
    private String start_time;
    private String duration;
    private String live_rtmp_url;
    private String live_http_flv_url;
    private String live_hls_url;
    private String thumbnail_path;
    private String record_flv;
    private String vod_flv_url;
    private String record_flv_path;
    private String record_mp4;
    private String record_mp4_path;
    private String record_ts;
    private String vod_ts_url;
    private String push_url;
    private String comment;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLive_rtmp_url() {
        return live_rtmp_url;
    }

    public void setLive_rtmp_url(String live_rtmp_url) {
        this.live_rtmp_url = live_rtmp_url;
    }

    public String getLive_http_flv_url() {
        return live_http_flv_url;
    }

    public void setLive_http_flv_url(String live_http_flv_url) {
        this.live_http_flv_url = live_http_flv_url;
    }

    public String getLive_hls_url() {
        return live_hls_url;
    }

    public void setLive_hls_url(String live_hls_url) {
        this.live_hls_url = live_hls_url;
    }

    public String getThumbnail_path() {
        return thumbnail_path;
    }

    public void setThumbnail_path(String thumbnail_path) {
        this.thumbnail_path = thumbnail_path;
    }

    public String getRecord_flv() {
        return record_flv;
    }

    public void setRecord_flv(String record_flv) {
        this.record_flv = record_flv;
    }

    public String getVod_flv_url() {
        return vod_flv_url;
    }

    public void setVod_flv_url(String vod_flv_url) {
        this.vod_flv_url = vod_flv_url;
    }

    public String getRecord_flv_path() {
        return record_flv_path;
    }

    public void setRecord_flv_path(String record_flv_path) {
        this.record_flv_path = record_flv_path;
    }

    public String getRecord_mp4() {
        return record_mp4;
    }

    public void setRecord_mp4(String record_mp4) {
        this.record_mp4 = record_mp4;
    }

    public String getRecord_mp4_path() {
        return record_mp4_path;
    }

    public void setRecord_mp4_path(String record_mp4_path) {
        this.record_mp4_path = record_mp4_path;
    }

    public String getRecord_ts() {
        return record_ts;
    }

    public void setRecord_ts(String record_ts) {
        this.record_ts = record_ts;
    }

    public String getVod_ts_url() {
        return vod_ts_url;
    }

    public void setVod_ts_url(String vod_ts_url) {
        this.vod_ts_url = vod_ts_url;
    }

    public String getPush_url() {
        return push_url;
    }

    public void setPush_url(String push_url) {
        this.push_url = push_url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

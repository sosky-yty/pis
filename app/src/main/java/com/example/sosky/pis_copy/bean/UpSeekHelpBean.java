package com.example.sosky.pis_copy.bean;

import java.util.List;

/**
 * 临时救助
 */
public class UpSeekHelpBean {

    private List<infoBean> infoBeans;

    public List<infoBean> getInfoBeans() {
        return infoBeans;
    }

    public void setInfoBeans(List<infoBean> infoBeans) {
        this.infoBeans = infoBeans;
    }

    public static class infoBean{
        private String ord2_hz;
        private String ord2_hzsfz;
        private String ord2_jzsj;
        private String ord2_jzyy;
        private String ord2_jzfs;
        private String ord2_bz;
        private String ord2_flag;
        private String ord2_yl;

        public String getOrd2_hz() {
            return ord2_hz;
        }

        public void setOrd2_hz(String ord2_hz) {
            this.ord2_hz = ord2_hz;
        }

        public String getOrd2_hzsfz() {
            return ord2_hzsfz;
        }

        public void setOrd2_hzsfz(String ord2_hzsfz) {
            this.ord2_hzsfz = ord2_hzsfz;
        }

        public String getOrd2_jzsj() {
            return ord2_jzsj;
        }

        public void setOrd2_jzsj(String ord2_jzsj) {
            this.ord2_jzsj = ord2_jzsj;
        }

        public String getOrd2_jzyy() {
            return ord2_jzyy;
        }

        public void setOrd2_jzyy(String ord2_jzyy) {
            this.ord2_jzyy = ord2_jzyy;
        }

        public String getOrd2_jzfs() {
            return ord2_jzfs;
        }

        public void setOrd2_jzfs(String ord2_jzfs) {
            this.ord2_jzfs = ord2_jzfs;
        }

        public String getOrd2_bz() {
            return ord2_bz;
        }

        public void setOrd2_bz(String ord2_bz) {
            this.ord2_bz = ord2_bz;
        }

        public String getOrd2_flag() {
            return ord2_flag;
        }

        public void setOrd2_flag(String ord2_flag) {
            this.ord2_flag = ord2_flag;
        }

        public String getOrd2_yl() {
            return ord2_yl;
        }

        public void setOrd2_yl(String ord2_yl) {
            this.ord2_yl = ord2_yl;
        }
    }
}

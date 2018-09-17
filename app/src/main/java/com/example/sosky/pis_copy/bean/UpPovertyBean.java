package com.example.sosky.pis_copy.bean;

import java.util.List;

/**
 * 精准扶贫
 */
public class UpPovertyBean {

    List<InfoBean> info;

   public List<InfoBean> getInfoBeans() {        return info;    }

   public void setInfoBeans(List<InfoBean> infoBeans) {        this.info = infoBeans;    }

    public static class InfoBean{
        private String ord2_hz;
        private String ord2_hzsfz;
        private String ord2_bfzrr;
        private String ord2_bfzrrszdw;
        private String ord2_bfzrrlxfs;
        private String ord2_zcgzdyxm;
        private String ord2_dysj;
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

        public String getOrd2_bfzrr() {
            return ord2_bfzrr;
        }

        public void setOrd2_bfzrr(String ord2_bfzrr) {
            this.ord2_bfzrr = ord2_bfzrr;
        }

        public String getOrd2_bfzrrszdw() {
            return ord2_bfzrrszdw;
        }

        public void setOrd2_bfzrrszdw(String ord2_bfzrrszdw) {
            this.ord2_bfzrrszdw = ord2_bfzrrszdw;
        }

        public String getOrd2_bfzrrlxfs() {
            return ord2_bfzrrlxfs;
        }

        public void setOrd2_bfzrrlxfs(String ord2_bfzrrlxfs) {
            this.ord2_bfzrrlxfs = ord2_bfzrrlxfs;
        }

        public String getOrd2_zcgzdyxm() {
            return ord2_zcgzdyxm;
        }

        public void setOrd2_zcgzdyxm(String ord2_zcgzdyxm) {
            this.ord2_zcgzdyxm = ord2_zcgzdyxm;
        }

        public String getOrd2_dysj() {
            return ord2_dysj;
        }

        public void setOrd2_dysj(String ord2_dysj) {
            this.ord2_dysj = ord2_dysj;
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

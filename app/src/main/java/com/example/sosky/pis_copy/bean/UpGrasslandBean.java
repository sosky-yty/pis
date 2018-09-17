package com.example.sosky.pis_copy.bean;

import java.util.List;

/**
 * 草原补助
 */
public class UpGrasslandBean {
    List<InfoBean> info;

   public List<InfoBean> getInfoBeans() {        return info;    }

   public void setInfoBeans(List<InfoBean> infoBeans) {        this.info = infoBeans;    }

    public static class InfoBean{
        private String ord2_hz;
        private String ord2_hzsfz;
        private String ord2_nf;
        private String ord2_je;
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

        public String getOrd2_nf() {
            return ord2_nf;
        }

        public void setOrd2_nf(String ord2_nf) {
            this.ord2_nf = ord2_nf;
        }

        public String getOrd2_je() {
            return ord2_je;
        }

        public void setOrd2_je(String ord2_je) {
            this.ord2_je = ord2_je;
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

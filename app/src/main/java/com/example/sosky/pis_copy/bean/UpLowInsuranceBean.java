package com.example.sosky.pis_copy.bean;

import java.util.List;

/**
 * 低保
 */
public class UpLowInsuranceBean {

    List<InfoBean> infoBeans;

    public List<InfoBean> getInfoBeans() {
        return infoBeans;
    }

    public void setInfoBeans(List<InfoBean> infoBeans) {
        this.infoBeans = infoBeans;
    }

    public static class InfoBean{
        private String ord2_hz;
        private String ord2_hzsfz;
        private String ord2_sfxsdb;
        private String ord2_xsnf;
        private String ord2_dblb;
        private String ord2_xsje;
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

        public String getOrd2_sfxsdb() {
            return ord2_sfxsdb;
        }

        public void setOrd2_sfxsdb(String ord2_sfxsdb) {
            this.ord2_sfxsdb = ord2_sfxsdb;
        }

        public String getOrd2_xsnf() {
            return ord2_xsnf;
        }

        public void setOrd2_xsnf(String ord2_xsnf) {
            this.ord2_xsnf = ord2_xsnf;
        }

        public String getOrd2_dblb() {
            return ord2_dblb;
        }

        public void setOrd2_dblb(String ord2_dblb) {
            this.ord2_dblb = ord2_dblb;
        }

        public String getOrd2_xsje() {
            return ord2_xsje;
        }

        public void setOrd2_xsje(String ord2_xsje) {
            this.ord2_xsje = ord2_xsje;
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

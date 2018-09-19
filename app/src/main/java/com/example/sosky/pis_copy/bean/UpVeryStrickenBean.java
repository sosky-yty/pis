package com.example.sosky.pis_copy.bean;

import java.util.List;

/**
 * 特困信息
 */
public class UpVeryStrickenBean {

    List<InfoBean> info;

    public List<InfoBean> getInfoBeans() {
        return info;
    }

    public void setInfoBeans(List<InfoBean> infoBeans) {
        this.info = infoBeans;
    }

    public static class InfoBean {
        private String ord2_xm;
        private String ord2_sfz;
        private String ord2_sfxsnctk;
        private String ord2_nctkxsnx;
        private String ord2_sfxscstk;
        private String ord2_cstkxsnx;
        private String ord2_xsje;
        private String ord2_gyfs;
        private String ord2_bz;
        private String ord2_flag;
        private String ord2_yl;

        public String getOrd2_yl() {
            return ord2_yl;
        }

        public void setOrd2_yl(String ord2_yl) {
            this.ord2_yl = ord2_yl;
        }

        public String getOrd2_xm() {
            return ord2_xm;
        }

        public void setOrd2_xm(String ord2_xm) {
            this.ord2_xm = ord2_xm;
        }

        public String getOrd2_sfz() {
            return ord2_sfz;
        }

        public void setOrd2_sfz(String ord2_sfz) {
            this.ord2_sfz = ord2_sfz;
        }

        public String getOrd2_sfxsnctk() {
            return ord2_sfxsnctk;
        }

        public void setOrd2_sfxsnctk(String ord2_sfxsnctk) {
            this.ord2_sfxsnctk = ord2_sfxsnctk;
        }

        public String getOrd2_nctkxsnx() {
            return ord2_nctkxsnx;
        }

        public void setOrd2_nctkxsnx(String ord2_nctkxsnx) {
            this.ord2_nctkxsnx = ord2_nctkxsnx;
        }

        public String getOrd2_sfxscstk() {
            return ord2_sfxscstk;
        }

        public void setOrd2_sfxscstk(String ord2_sfxscstk) {
            this.ord2_sfxscstk = ord2_sfxscstk;
        }

        public String getOrd2_cstkxsnx() {
            return ord2_cstkxsnx;
        }

        public void setOrd2_cstkxsnx(String ord2_cstkxsnx) {
            this.ord2_cstkxsnx = ord2_cstkxsnx;
        }

        public String getOrd2_xsje() {
            return ord2_xsje;
        }

        public void setOrd2_xsje(String ord2_xsje) {
            this.ord2_xsje = ord2_xsje;
        }

        public String getOrd2_gyfs() {
            return ord2_gyfs;
        }

        public void setOrd2_gyfs(String ord2_gyfs) {
            this.ord2_gyfs = ord2_gyfs;
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
    }
}

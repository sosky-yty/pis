package com.example.sosky.pis_copy.bean;

import java.util.List;

/**
 * 残联信息
 */
public class UpResidualBean {
    List<InfoBean> infoBeans;

    public List<InfoBean> getInfoBeans() {
        return infoBeans;
    }

    public void setInfoBeans(List<InfoBean> infoBeans) {
        this.infoBeans = infoBeans;
    }

    public static class InfoBean{
        private String ord2_xm;
        private String ord2_sfz;
        private String ord2_cjzzh;
        private String ord2_cjdj;
        private String ord2_cjyy;
        private String ord2_cjlb;
        private String ord2_sfxszc;
        private String ord2_zclb;

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

        public String getOrd2_cjzzh() {
            return ord2_cjzzh;
        }

        public void setOrd2_cjzzh(String ord2_cjzzh) {
            this.ord2_cjzzh = ord2_cjzzh;
        }

        public String getOrd2_cjdj() {
            return ord2_cjdj;
        }

        public void setOrd2_cjdj(String ord2_cjdj) {
            this.ord2_cjdj = ord2_cjdj;
        }

        public String getOrd2_cjyy() {
            return ord2_cjyy;
        }

        public void setOrd2_cjyy(String ord2_cjyy) {
            this.ord2_cjyy = ord2_cjyy;
        }

        public String getOrd2_cjlb() {
            return ord2_cjlb;
        }

        public void setOrd2_cjlb(String ord2_cjlb) {
            this.ord2_cjlb = ord2_cjlb;
        }

        public String getOrd2_sfxszc() {
            return ord2_sfxszc;
        }

        public void setOrd2_sfxszc(String ord2_sfxszc) {
            this.ord2_sfxszc = ord2_sfxszc;
        }

        public String getOrd2_zclb() {
            return ord2_zclb;
        }

        public void setOrd2_zclb(String ord2_zclb) {
            this.ord2_zclb = ord2_zclb;
        }
    }
}

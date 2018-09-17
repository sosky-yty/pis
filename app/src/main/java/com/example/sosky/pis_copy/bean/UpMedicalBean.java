package com.example.sosky.pis_copy.bean;

import java.util.List;

/**
 * 医保信息
 */
public class UpMedicalBean {
    public static class InfoBean {
        private String ord2_xm;
        private String ord2_sfz;
        private String ord2_sccyybrq;
        private String ord2_ryfl;
        private String ord2_zjdc;
        private String ord2_flag;
        private String ord2_yl;

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

        public String getOrd2_sccyybrq() {
            return ord2_sccyybrq;
        }

        public void setOrd2_sccyybrq(String ord2_sccyybrq) {
            this.ord2_sccyybrq = ord2_sccyybrq;
        }

        public String getOrd2_ryfl() {
            return ord2_ryfl;
        }

        public void setOrd2_ryfl(String ord2_ryfl) {
            this.ord2_ryfl = ord2_ryfl;
        }

        public String getOrd2_zjdc() {
            return ord2_zjdc;
        }

        public void setOrd2_zjdc(String ord2_zjdc) {
            this.ord2_zjdc = ord2_zjdc;
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

    List<InfoBean> info;

   public List<InfoBean> getInfoBeans() {        return info;    }

   public void setInfoBeans(List<InfoBean> infoBeans) {        this.info = infoBeans;    }
}

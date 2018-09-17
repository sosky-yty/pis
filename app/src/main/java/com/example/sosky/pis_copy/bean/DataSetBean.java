package com.example.sosky.pis_copy.bean;

import java.util.List;

public class DataSetBean {

    /**
     * dataset : {"info":[{}]}
     */

    private DatasetBean dataset;

    public DatasetBean getDataset() {
        return dataset;
    }

    public void setDataset(DatasetBean dataset) {
        this.dataset = dataset;
    }

    public static class DatasetBean {
        private List<InfoBean> info;

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
        }
    }
}

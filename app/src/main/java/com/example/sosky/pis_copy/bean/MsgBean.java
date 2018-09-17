package com.example.sosky.pis_copy.bean;

public class MsgBean {

    /**
     * dataset : {"t1":{"code":"0","result":"error","message":"错误原因:临时表中存在未审核数据!"}}
     */

    private DatasetBean dataset;

    public DatasetBean getDataset() {
        return dataset;
    }

    public void setDataset(DatasetBean dataset) {
        this.dataset = dataset;
    }

    public static class DatasetBean {
        /**
         * t1 : {"code":"0","result":"error","message":"错误原因:临时表中存在未审核数据!"}
         */

        private T1Bean t1;

        public T1Bean getT1() {
            return t1;
        }

        public void setT1(T1Bean t1) {
            this.t1 = t1;
        }

        public static class T1Bean {
            /**
             * code : 0
             * result : error
             * message : 错误原因:临时表中存在未审核数据!
             */

            private int code;
            private String result;
            private String message;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getResult() {
                return result;
            }

            public void setResult(String result) {
                this.result = result;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }
        }
    }
}

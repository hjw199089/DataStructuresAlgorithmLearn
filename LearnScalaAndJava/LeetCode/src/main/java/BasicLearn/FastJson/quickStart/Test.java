package com.scalalearn.java.main.FastJson.quickStart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hjw on 17/4/10.
 */
public class Test {
    public static class CtypeInfo {
        String ctype;
        int cnt;

        public String getCtype() {
            return ctype;
        }

        public void setCtype(String ctype) {
            this.ctype = ctype;
        }

        public int getCnt() {
            return cnt;
        }

        public void setCnt(int cnt) {
            this.cnt = cnt;
        }

        public CtypeInfo(String ctype, int cnt) {
            this.ctype = ctype;
            this.cnt = cnt;
        }

        public CtypeInfo() {
        }

    }

    public static class PartialResult {
        Set<String> ctype_arr = new HashSet<String>();
        List<CtypeInfo> ctype_vistInfo = new ArrayList<CtypeInfo>();
        String ctype_freq = "";
        String ctype_last = "";

        public Set getcCtypeSet() {
            return ctype_arr;
        }

        public List<CtypeInfo> getCtype_vistInfo() {
            return ctype_vistInfo;
        }

        public String getCtype_freq() {
            return ctype_freq;
        }

        public String getCtype_last() {
            return ctype_last;
        }


        public void setCtypeSet(Set ctypeSet) {
            this.ctype_arr = ctypeSet;
        }

        public void setCtype_vistInfo(List<CtypeInfo> ctype_vistInfo) {
            this.ctype_vistInfo = ctype_vistInfo;
        }

        public void setCtype_freq(String ctype_freq) {
            this.ctype_freq = ctype_freq;
        }

        public void setCtype_last(String ctype_last) {
            this.ctype_last = ctype_last;
        }

        @Override
        public String toString() {
            return "PartialResult{" +
                    "ctypeSet=" + ctype_arr +
                    ", ctype_vistInfo=" + ctype_vistInfo +
                    ", ctype_freq=" + ctype_freq +
                    ", ctype_last=" + ctype_last +
                    '}';
        }

        public void clear() {
            ctype_arr.clear();
            ctype_vistInfo.clear();
            ctype_freq = "";
            ctype_last = "";
        }
    }


    public static void main(String[] args) {
        PartialResult partial = new PartialResult();
        partial = JSON.parseObject("{\"ctypeSet\":[],\"ctype_freq\":\"0\",\"ctype_last\":\"0\",\"ctype_vistInfo\":[]}", PartialResult.class);
        System.out.println(JSON.toJSONString(partial));
        String s = "{\"meta\":{\"code\":0,\"searchStatus\":\"continue\",\"errorMsg\":\"任务已超时\"},\"data\":{\"queryId\":\"e51df025-8b5c-4a91-8cb3-473625f930ba\"}}";
        JSONObject job = JSON.parseObject(s);
        JSONObject meta = (JSONObject) job.get("meta");
        System.out.println(meta.getString("continue"));
    }

}

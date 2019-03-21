package BasicLearn.FastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



public class JsonArr {

  static void  printBlank(JSONObject obj) {
    int num = obj.getInteger("stageNum");
    while (num > 0) {
      System.out.print("\t");
    }
    int stageNum = obj.getInteger("stageNum");
    System.out.print("stageNum: " + stageNum + "\t");
    System.out.println(obj.getString("operatorName"));
    while (num > 0) {
      System.out.print("\t");
    }
    System.out.println(obj.toJSONString());
//    Object[] subPlanNodeList = obj.getJSONArray("subPlanNodeList").toArray();
//    for (Object iter :subPlanNodeList) {
//      System.out.println(iter.toString());
//    }
  }

  public static void main(String[] args) {

    String arr = "{\n" +
      "    \"dagOperatorList\": [\n" +
      "        {\n" +
      "            \"operatorName\": \"MergeOperator\",\n" +
      "            \"stageNum\": 0,\n" +
      "            \"subPlanNodeList\": {\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_0_13\": [\n" +
      "                    \"MergeSortNode\",\n" +
      "                    \"OutputNode\"\n" +
      "                ]\n" +
      "            },\n" +
      "            \"subplanFullIds\": [\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_0_13\"\n" +
      "            ]\n" +
      "        },\n" +
      "        {\n" +
      "            \"operatorName\": \"JoinOperator\",\n" +
      "            \"stageNum\": 1,\n" +
      "            \"subPlanNodeList\": {\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_10\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_11\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_12\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_3\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_4\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_5\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_6\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_7\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_8\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ],\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_9\": [\n" +
      "                    \"JoinNode\"\n" +
      "                ]\n" +
      "            },\n" +
      "            \"subplanFullIds\": [\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_3\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_4\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_7\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_11\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_8\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_10\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_5\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_6\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_12\",\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_1_9\"\n" +
      "            ]\n" +
      "        },\n" +
      "        {\n" +
      "            \"operatorName\": \"SelectOperator\",\n" +
      "            \"stageNum\": 2,\n" +
      "            \"subPlanNodeList\": {\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_2_2\": [\n" +
      "                    \"TableScanNode\",\n" +
      "                    \"ShuffleNode\"\n" +
      "                ]\n" +
      "            },\n" +
      "            \"subplanFullIds\": [\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_2_2\"\n" +
      "            ]\n" +
      "        },\n" +
      "        {\n" +
      "            \"operatorName\": \"SelectOperator\",\n" +
      "            \"stageNum\": 2,\n" +
      "            \"subPlanNodeList\": {\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_2_1\": [\n" +
      "                    \"TableScanNode\",\n" +
      "                    \"ShuffleNode\"\n" +
      "                ]\n" +
      "            },\n" +
      "            \"subplanFullIds\": [\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_2_1\"\n" +
      "            ]\n" +
      "        },\n" +
      "        {\n" +
      "            \"operatorName\": \"SelectOperator\",\n" +
      "            \"stageNum\": 2,\n" +
      "            \"subPlanNodeList\": {\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_2_0\": [\n" +
      "                    \"TableScanNode\",\n" +
      "                    \"ShuffleNode\"\n" +
      "                ]\n" +
      "            },\n" +
      "            \"subplanFullIds\": [\n" +
      "                \"65c024f2-69da-457e-adcc-418a5aaf825b_2_0\"\n" +
      "            ]\n" +
      "        }\n" +
      "    ],\n" +
      "    \"queryId\": \"65c024f2-69da-457e-adcc-418a5aaf825b\",\n" +
      "    \"rootOperator\": \"MergeOperator\",\n" +
      "    \"stage2OpsTable\": {\n" +
      "        \"0\": [\n" +
      "            \"MergeOperator\"\n" +
      "        ],\n" +
      "        \"1\": [\n" +
      "            \"JoinOperator\"\n" +
      "        ],\n" +
      "        \"2\": [\n" +
      "            \"SelectOperator\",\n" +
      "            \"SelectOperator\",\n" +
      "            \"SelectOperator\"\n" +
      "        ]\n" +
      "    },\n" +
      "    \"stageList\": [\n" +
      "        0,\n" +
      "        1,\n" +
      "        2\n" +
      "    ]\n" +
      "}\n";

    JSONObject object = (JSONObject) JSON.parse(arr);
    Object[] jsonArray = object.getJSONArray("dagOperatorList").toArray();

    for (Object iter : jsonArray) {
      printBlank((JSONObject) iter);
    }


  }


}

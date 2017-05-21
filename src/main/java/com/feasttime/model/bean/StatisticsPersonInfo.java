/*
 * Copyright (c) 2017. sheng yan
 */

package com.feasttime.model.bean;

import java.util.List;

public class StatisticsPersonInfo {

    /**
     * resultCode : 0
     * eatCount : 20
     * eatType : [{"type":"中餐","percent":"80%"},{"type":"西餐","percent":"80%"},{"type":"日餐","percent":"80%"}]
     * healthTips : 健康分析:您最近身体不错
     * healthAnalysisChart : [{"fat":[{"yAxisNum":"200","xAxisNum":"1","xAxisText":"6月1日"},{"yAxisNum":"263","xAxisNum":"2","xAxisText":"6月2日"},{"yAxisNum":"369","xAxisNum":"3","xAxisText":"6月3日"},{"yAxisNum":"125","xAxisNum":"4","xAxisText":"6月5日"},{"yAxisNum":"784","xAxisNum":"5","xAxisText":"6月7日"}],"carbohydrate":[{"yAxisNum":"200","xAxisNum":"1","xAxisText":"6月1日"},{"yAxisNum":"263","xAxisNum":"2","xAxisText":"6月2日"},{"yAxisNum":"369","xAxisNum":"3","xAxisText":"6月3日"},{"yAxisNum":"125","xAxisNum":"4","xAxisText":"6月5日"},{"yAxisNum":"784","xAxisNum":"5","xAxisText":"6月7日"}],"protein":[{"yAxisNum":"200","xAxisNum":"1","xAxisText":"6月1日"},{"yAxisNum":"263","xAxisNum":"2","xAxisText":"6月2日"},{"yAxisNum":"369","xAxisNum":"3","xAxisText":"6月3日"},{"yAxisNum":"125","xAxisNum":"4","xAxisText":"6月5日"},{"yAxisNum":"784","xAxisNum":"5","xAxisText":"6月7日"}],"sodium":[{"yAxisNum":"200","xAxisNum":"1","xAxisText":"6月1日"},{"yAxisNum":"263","xAxisNum":"2","xAxisText":"6月2日"},{"yAxisNum":"369","xAxisNum":"3","xAxisText":"6月3日"},{"yAxisNum":"125","xAxisNum":"4","xAxisText":"6月5日"},{"yAxisNum":"784","xAxisNum":"5","xAxisText":"6月7日"}]}]
     * consumeTips : 消费分析:上个月花少了
     * consumeChart : [{"yAxisNum1":"200","yAxisNum2":"354","xAxisNum":"1","xAxisText":"6月1日"},{"yAxisNum1":"263","yAxisNum2":"125","xAxisNum":"2","xAxisText":"6月2日"},{"yAxisNum1":"369","yAxisNum2":"854","xAxisNum":"3","xAxisText":"6月3日"},{"yAxisNum1":"125","yAxisNum2":"365","xAxisNum":"4","xAxisText":"6月5日"},{"yAxisNum1":"784","yAxisNum2":"254","xAxisNum":"5","xAxisText":"6月7日"}]
     * lastMonthEatPercent : 31%
     * thisMonthEatPercent : 45%
     * eatAnalysis : 外出就餐频率分析
     */

    private int resultCode;
    private String eatCount;
    private String healthTips;
    private String consumeTips;
    private String lastMonthEatPercent;
    private String thisMonthEatPercent;
    private String eatAnalysis;
    private List<EatTypeBean> eatType;
    private List<HealthAnalysisChartBean> healthAnalysisChart;
    private List<ConsumeChartBean> consumeChart;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getEatCount() {
        return eatCount;
    }

    public void setEatCount(String eatCount) {
        this.eatCount = eatCount;
    }

    public String getHealthTips() {
        return healthTips;
    }

    public void setHealthTips(String healthTips) {
        this.healthTips = healthTips;
    }

    public String getConsumeTips() {
        return consumeTips;
    }

    public void setConsumeTips(String consumeTips) {
        this.consumeTips = consumeTips;
    }

    public String getLastMonthEatPercent() {
        return lastMonthEatPercent;
    }

    public void setLastMonthEatPercent(String lastMonthEatPercent) {
        this.lastMonthEatPercent = lastMonthEatPercent;
    }

    public String getThisMonthEatPercent() {
        return thisMonthEatPercent;
    }

    public void setThisMonthEatPercent(String thisMonthEatPercent) {
        this.thisMonthEatPercent = thisMonthEatPercent;
    }

    public String getEatAnalysis() {
        return eatAnalysis;
    }

    public void setEatAnalysis(String eatAnalysis) {
        this.eatAnalysis = eatAnalysis;
    }

    public List<EatTypeBean> getEatType() {
        return eatType;
    }

    public void setEatType(List<EatTypeBean> eatType) {
        this.eatType = eatType;
    }

    public List<HealthAnalysisChartBean> getHealthAnalysisChart() {
        return healthAnalysisChart;
    }

    public void setHealthAnalysisChart(List<HealthAnalysisChartBean> healthAnalysisChart) {
        this.healthAnalysisChart = healthAnalysisChart;
    }

    public List<ConsumeChartBean> getConsumeChart() {
        return consumeChart;
    }

    public void setConsumeChart(List<ConsumeChartBean> consumeChart) {
        this.consumeChart = consumeChart;
    }

    public static class EatTypeBean {
        /**
         * type : 中餐
         * percent : 80%
         */

        private String type;
        private String percent;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPercent() {
            return percent;
        }

        public void setPercent(String percent) {
            this.percent = percent;
        }
    }

    public static class HealthAnalysisChartBean {
        private List<LineChartBean> fat;
        private List<LineChartBean> carbohydrate;
        private List<LineChartBean> protein;
        private List<LineChartBean> sodium;

        public List<LineChartBean> getFat() {
            return fat;
        }

        public void setFat(List<LineChartBean> fat) {
            this.fat = fat;
        }

        public List<LineChartBean> getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(List<LineChartBean> carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public List<LineChartBean> getProtein() {
            return protein;
        }

        public void setProtein(List<LineChartBean> protein) {
            this.protein = protein;
        }

        public List<LineChartBean> getSodium() {
            return sodium;
        }

        public void setSodium(List<LineChartBean> sodium) {
            this.sodium = sodium;
        }

        public static class LineChartBean {
            /**
             * yAxisNum : 200
             * xAxisNum : 1
             * xAxisText : 6月1日
             */

            private String yAxisNum;
            private String xAxisNum;
            private String xAxisText;

            public String getYAxisNum() {
                return yAxisNum;
            }

            public void setYAxisNum(String yAxisNum) {
                this.yAxisNum = yAxisNum;
            }

            public String getXAxisNum() {
                return xAxisNum;
            }

            public void setXAxisNum(String xAxisNum) {
                this.xAxisNum = xAxisNum;
            }

            public String getXAxisText() {
                return xAxisText;
            }

            public void setXAxisText(String xAxisText) {
                this.xAxisText = xAxisText;
            }
        }

        public static class CarbohydrateBean {
            /**
             * yAxisNum : 200
             * xAxisNum : 1
             * xAxisText : 6月1日
             */

            private String yAxisNum;
            private String xAxisNum;
            private String xAxisText;

            public String getYAxisNum() {
                return yAxisNum;
            }

            public void setYAxisNum(String yAxisNum) {
                this.yAxisNum = yAxisNum;
            }

            public String getXAxisNum() {
                return xAxisNum;
            }

            public void setXAxisNum(String xAxisNum) {
                this.xAxisNum = xAxisNum;
            }

            public String getXAxisText() {
                return xAxisText;
            }

            public void setXAxisText(String xAxisText) {
                this.xAxisText = xAxisText;
            }
        }

        public static class ProteinBean {
            /**
             * yAxisNum : 200
             * xAxisNum : 1
             * xAxisText : 6月1日
             */

            private String yAxisNum;
            private String xAxisNum;
            private String xAxisText;

            public String getYAxisNum() {
                return yAxisNum;
            }

            public void setYAxisNum(String yAxisNum) {
                this.yAxisNum = yAxisNum;
            }

            public String getXAxisNum() {
                return xAxisNum;
            }

            public void setXAxisNum(String xAxisNum) {
                this.xAxisNum = xAxisNum;
            }

            public String getXAxisText() {
                return xAxisText;
            }

            public void setXAxisText(String xAxisText) {
                this.xAxisText = xAxisText;
            }
        }

        public static class SodiumBean {
            /**
             * yAxisNum : 200
             * xAxisNum : 1
             * xAxisText : 6月1日
             */

            private String yAxisNum;
            private String xAxisNum;
            private String xAxisText;

            public String getYAxisNum() {
                return yAxisNum;
            }

            public void setYAxisNum(String yAxisNum) {
                this.yAxisNum = yAxisNum;
            }

            public String getXAxisNum() {
                return xAxisNum;
            }

            public void setXAxisNum(String xAxisNum) {
                this.xAxisNum = xAxisNum;
            }

            public String getXAxisText() {
                return xAxisText;
            }

            public void setXAxisText(String xAxisText) {
                this.xAxisText = xAxisText;
            }
        }
    }

    public static class ConsumeChartBean {
        /**
         * yAxisNum1 : 200
         * yAxisNum2 : 354
         * xAxisNum : 1
         * xAxisText : 6月1日
         */

        private String yAxisNum1;
        private String yAxisNum2;
        private String xAxisNum;
        private String xAxisText;

        public String getYAxisNum1() {
            return yAxisNum1;
        }

        public void setYAxisNum1(String yAxisNum1) {
            this.yAxisNum1 = yAxisNum1;
        }

        public String getYAxisNum2() {
            return yAxisNum2;
        }

        public void setYAxisNum2(String yAxisNum2) {
            this.yAxisNum2 = yAxisNum2;
        }

        public String getXAxisNum() {
            return xAxisNum;
        }

        public void setXAxisNum(String xAxisNum) {
            this.xAxisNum = xAxisNum;
        }

        public String getXAxisText() {
            return xAxisText;
        }

        public void setXAxisText(String xAxisText) {
            this.xAxisText = xAxisText;
        }
    }
}

package com.eastsoft.esgjyj.domain;

import java.math.BigDecimal;

public class Ajmx {
    private String id;

    private String khdxid;

    private String colIndex;

    private String lb;

    private Long caseSn;

    private BigDecimal score;

    private BigDecimal averageScore;

    private String dfsm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getKhdxid() {
        return khdxid;
    }

    public void setKhdxid(String khdxid) {
        this.khdxid = khdxid == null ? null : khdxid.trim();
    }

    public String getColIndex() {
        return colIndex;
    }

    public void setColIndex(String colIndex) {
        this.colIndex = colIndex == null ? null : colIndex.trim();
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb == null ? null : lb.trim();
    }

    public Long getCaseSn() {
        return caseSn;
    }

    public void setCaseSn(Long caseSn) {
        this.caseSn = caseSn;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public BigDecimal getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(BigDecimal averageScore) {
        this.averageScore = averageScore;
    }

    public String getDfsm() {
        return dfsm;
    }

    public void setDfsm(String dfsm) {
        this.dfsm = dfsm == null ? null : dfsm.trim();
    }
}
package com.design.pattern.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RunoobTb {

    private  Integer runoobId;
    private  String runoobTitle;
    private  String runoobAuthor;
    private  LocalDateTime submissionDate;

    public RunoobTb(Builder builder){
        this.runoobId=builder.runoobId;
        this.runoobTitle=builder.runoobTitle;
        this.runoobAuthor=builder.runoobAuthor;
        this.submissionDate=builder.submissionDate;
    }
    public static final class Builder{
        private  Integer runoobId;
        private  String runoobTitle;
        private  String runoobAuthor;
        private  LocalDateTime submissionDate;
        public Builder runoobId(Integer runoobId){
            this.runoobId=runoobId;
            return this;
        }
        public Builder runoobTitle(String runoobTitle){
            this.runoobTitle=runoobTitle;
            return this;
        }
        public Builder runoobAuthor(String runoobAuthor){
            this.runoobAuthor=runoobAuthor;
            return this;
        }
        public Builder submissionDate(LocalDateTime submissionDate){
            this.submissionDate=submissionDate;
            return this;
        }
        public RunoobTb build(){
            return new RunoobTb(this);
        }
    }


}

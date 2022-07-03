package com.example.core;

public class GetEvents {
    String topic,person,date,time,duration,description;
    public GetEvents(String topic, String person, String date, String time, String duration, String description)
    {
        this.topic=topic;
        this.person=person;
        this.date=date;
        this.time=time;
        this.duration=duration;
        this.description=description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

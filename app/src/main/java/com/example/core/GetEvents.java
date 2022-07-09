package com.example.core;

import android.os.Parcel;
import android.os.Parcelable;

public class GetEvents implements Parcelable {
    private String Topic,Designation,Person,Date,StartTime,EndTime,Venue,Description,EventHandlerPh1,EventHandlerPh2,EventHandlerName1,EventHandlerName2,EventsId,FormLink,PosterLink;

    public GetEvents(){

    }
    public GetEvents(String topic, String designation, String person, String date, String startTime, String endTime, String venue, String description, String eventHandlerPh1, String eventHandlerPh2, String eventHandlerName1, String eventHandlerName2, String eventsId, String formLink, String posterLink) {
        Topic = topic;
        Designation = designation;
        Person = person;
        Date = date;
        StartTime = startTime;
        EndTime = endTime;
        Venue = venue;
        Description = description;
        EventHandlerPh1 = eventHandlerPh1;
        EventHandlerPh2 = eventHandlerPh2;
        EventHandlerName1 = eventHandlerName1;
        EventHandlerName2 = eventHandlerName2;
        EventsId = eventsId;
        FormLink=formLink;
        PosterLink=posterLink;
    }

    protected GetEvents(Parcel in) {
        Topic = in.readString();
        Designation = in.readString();
        Person = in.readString();
        Date = in.readString();
        StartTime = in.readString();
        EndTime = in.readString();
        Venue = in.readString();
        Description = in.readString();
        EventHandlerPh1 = in.readString();
        EventHandlerPh2 = in.readString();
        EventHandlerName1 = in.readString();
        EventHandlerName2 = in.readString();
        EventsId = in.readString();
        FormLink=in.readString();
        PosterLink=in.readString();
    }

    public static final Creator<GetEvents> CREATOR = new Creator<GetEvents>() {
        @Override
        public GetEvents createFromParcel(Parcel in) {
            return new GetEvents(in);
        }

        @Override
        public GetEvents[] newArray(int size) {
            return new GetEvents[size];
        }
    };

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getPerson() {
        return Person;
    }

    public void setPerson(String person) {
        Person = person;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getVenue() {
        return Venue;
    }

    public void setVenue(String venue) {
        Venue = venue;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEventHandlerPh1() {
        return EventHandlerPh1;
    }

    public void setEventHandlerPh1(String eventHandlerPh1) {
        EventHandlerPh1 = eventHandlerPh1;
    }

    public String getEventHandlerPh2() {
        return EventHandlerPh2;
    }

    public void setEventHandlerPh2(String eventHandlerPh2) {
        EventHandlerPh2 = eventHandlerPh2;
    }

    public String getEventHandlerName1() {
        return EventHandlerName1;
    }

    public void setEventHandlerName1(String eventHandlerName1) {
        EventHandlerName1 = eventHandlerName1;
    }

    public String getEventHandlerName2() {
        return EventHandlerName2;
    }

    public void setEventHandlerName2(String eventHandlerName2) {
        EventHandlerName2 = eventHandlerName2;
    }

    public String getEventsId() {
        return EventsId;
    }

    public void setEventsId(String eventsId) {
        EventsId = eventsId;
    }

    public String getFormLink() {
        return FormLink;
    }

    public void setFormLink(String formLink) {
        FormLink = formLink;
    }

    public String getPosterLink() {
        return PosterLink;
    }

    public void setPosterLink(String posterLink) {
        PosterLink = posterLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Topic);
        parcel.writeString(Designation);
        parcel.writeString(Person);
        parcel.writeString(Date);
        parcel.writeString(StartTime);
        parcel.writeString(EndTime);
        parcel.writeString(Venue);
        parcel.writeString(Description);
        parcel.writeString(EventHandlerPh1);
        parcel.writeString(EventHandlerPh2);
        parcel.writeString(EventHandlerName1);
        parcel.writeString(EventHandlerName2);
        parcel.writeString(EventsId);
        parcel.writeString(FormLink);
        parcel.writeString(PosterLink);
    }
}

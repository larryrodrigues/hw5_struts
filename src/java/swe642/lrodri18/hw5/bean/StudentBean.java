/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe642.lrodri18.hw5.bean;

import java.util.Date;

/**
 *
 * @author LRODRIGUES
 */
public class StudentBean {

    private int studentSeqId = -1;
    private String studentId = null;
    private String username = null;
    private String email = null;
    private String nameFirstlast = null;
    private String streetAddress = null;
    private String city = null;
    private String state = null;
    private String zip = null;
    private String phoneNumber = null;
    private String surveyDate = null;
    private String homepageUrl = null;
    private String hsGradYear = null;
    private String hsGradMonth = null;
    private String campusLikes = null;
    private String interestInd = null;
    private String recLikely = null;
    private String movieTitle = null;
    private int    movieRating = -1;
    private String dataField = null;
    private String comments = null;
    private java.util.Date ratingsTs = null;
    
    

    public int getStudentSeqId() {
        return studentSeqId;
    }

    public void setStudentSeqId(int studentSeqId) {
        this.studentSeqId = studentSeqId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if( studentId != null && !studentId.trim().equals("")) {
            studentId = studentId.trim().toUpperCase();
        } else {
            studentId = "";
        }
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if( username != null && !username.trim().equals("")) {
            username = username.trim().toUpperCase();
        } else {
            username = "";
        }
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if( email != null && !email.trim().equals("")) {
            email = email.trim().toUpperCase();
        } else {
            email = "";
        }
        this.email = email;
    }

    public String getNameFirstlast() {
        return nameFirstlast;
    }

    public void setNameFirstlast(String nameFirstlast) {
        if( nameFirstlast != null && !nameFirstlast.trim().equals("")) {
            nameFirstlast = nameFirstlast.trim().toUpperCase();
        } else {
            nameFirstlast = "";
        }
        this.nameFirstlast = nameFirstlast;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        if( streetAddress != null && !streetAddress.trim().equals("")) {
            streetAddress = streetAddress.trim().toUpperCase();
        } else {
            streetAddress = "";
        }
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if( city != null && !city.trim().equals("")) {
            city = city.trim().toUpperCase();
        } else {
            city = "";
        }
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if( state != null && !state.trim().equals("")) {
            state = state.trim().toUpperCase();
        } else {
            state = "";
        }
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        if( zip != null && !zip.trim().equals("")) {
            zip = zip.trim().toUpperCase();
        } else {
            zip = "";
        }
        this.zip = zip;
    }

    public String getPhoneNumber() {
        
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if( phoneNumber != null && !phoneNumber.trim().equals("")) {
            phoneNumber = phoneNumber.trim().toUpperCase();
        } else {
            phoneNumber = "";
        }
        this.phoneNumber = phoneNumber;
    }

    public String getSurveyDate() {
        return surveyDate;
    }
    
    public void setSurveyDate(String surveyDate) {
        if( surveyDate != null && !surveyDate.trim().equals("")) {
            surveyDate = surveyDate.trim().toUpperCase();
        } else {
            surveyDate = "";
        }
        this.surveyDate = surveyDate;
    }

    public void setHomepageUrl(String newData) {
      if( newData != null && !newData.trim().equals("")) {
        newData = newData.trim();
      } else {
        newData = "";
      }
      this.homepageUrl = newData;
    }
    
    public String getHomepageUrl() {
      return this.homepageUrl;
    }
    
    public String getHsGradYear() {
        return hsGradYear;
    }

    public void setHsGradYear(String hsGradYear) {
        if( hsGradYear != null && !hsGradYear.trim().equals("")) {
            hsGradYear = hsGradYear.trim().toUpperCase();
        } else {
            hsGradYear = "";
        }
        this.hsGradYear = hsGradYear;
    }

    public String getHsGradMonth() {
        return hsGradMonth;
    }

    public void setHsGradMonth(String hsGradMonth) {
        if( hsGradMonth != null && !hsGradMonth.trim().equals("")) {
            hsGradMonth = hsGradMonth.trim().toUpperCase();
        } else {
            hsGradMonth = "";
        }
        this.hsGradMonth = hsGradMonth;
    }

    public String getCampusLikes() {
        return campusLikes;
    }

    public void setCampusLikes(String campusLikes) {
        if( campusLikes != null && !campusLikes.trim().equals("")) {
            campusLikes = campusLikes.trim().toUpperCase();
        } else {
            campusLikes = "";
        }
        this.campusLikes = campusLikes;
    }

    public String getInterestInd() {
        return interestInd;
    }

    public void setInterestInd(String interestInd) {
        if( interestInd != null && !interestInd.trim().equals("")) {
            interestInd = interestInd.trim().toUpperCase();
        } else {
            interestInd = "";
        }
        this.interestInd = interestInd;
    }

    public String getRecLikely() {
        return recLikely;
    }

    public void setRecLikely(String recLikely) {
        if( recLikely != null && !recLikely.trim().equals("")) {
            recLikely = recLikely.trim().toUpperCase();
        } else {
            recLikely = "";
        }
        this.recLikely = recLikely;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        if( movieTitle != null && !movieTitle.trim().equals("")) {
            movieTitle = movieTitle.trim().toUpperCase();
        } else {
            movieTitle = "";
        }
        this.movieTitle = movieTitle;
    }

    public int getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(int movieRating) {
        this.movieRating = movieRating;
    }

    public String getDataField() {
        return dataField;
    }

    public void setDataField(String dataField) {
        if( dataField != null && !dataField.trim().equals("")) {
            dataField = dataField.trim().toUpperCase();
        } else {
            dataField = "";
        }
        this.dataField = dataField;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        if( comments != null && !comments.trim().equals("")) {
            comments = comments.trim().toUpperCase();
        } else {
            comments = "";
        }
        this.comments = comments;
    }

    public Date getRatingsTs() {
        return ratingsTs;
    }

    public void setRatingsTs(Date ratingsTs) {
        this.ratingsTs = ratingsTs;
    }

    // ==========================================================
    // Object Methods
    // ==========================================================
    public int compareTo(Object o) {
        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        // int retval = -1;
        
        if( o == null ) {
            return BEFORE;
        }
        if (!(o instanceof StudentBean)) {
            return BEFORE;
        }
        //this optimization is usually worthwhile, and can
        //always be added
        if (this == o) {
            return EQUAL;
        }

        //Performing explicit checks for nullity and type are made
        //redundant by the following cast, which will throw
        //NullPointerException and ClassCastException in these
        //respective cases.
        final StudentBean that = (StudentBean) o;

        return this.studentId.compareTo(that.getStudentId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentBean)) {
            return false;
        }

        StudentBean that = (StudentBean) o;
        return this.studentId.equals(that.getStudentId());
    }

    @Override
    public int hashCode() {
        // TODO Implement this method
        return this.studentId.hashCode();
    }

    @Override
    public String toString() {
        // TODO Implement this method
        return "[StudentDAO] {student_seq_id:" + this.studentSeqId + ", student_id:" + this.studentId + "}";
    }

}

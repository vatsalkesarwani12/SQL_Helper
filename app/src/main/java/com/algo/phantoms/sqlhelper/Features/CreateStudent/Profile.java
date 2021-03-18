package com.algo.phantoms.sqlhelper.Features.CreateStudent;

public class Profile {
    private long id;
    private String name;
    private String dob;
    private byte[] profile_picture;

    public Profile(int id, String name, String dob, byte[] profile_picture) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.profile_picture = profile_picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public byte[] getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(byte[] profile_picture) {
        this.profile_picture = profile_picture;
    }
}

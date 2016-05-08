package me.serendipify;

public class User {

  private String email;

  private User(String email) {
    this.email = email;
  }

  public String getEmail() {
    return this.email;
  }

  public static class Builder {

    private String email;

    private Builder() {
      // private, use getInstance() instead
    }

    public static Builder getInstance() {
      return new Builder();
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public User build() {
      return new User(this.email);
    }
  }
}

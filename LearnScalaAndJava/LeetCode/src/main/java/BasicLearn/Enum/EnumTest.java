package BasicLearn.Enum;

public class EnumTest {
  public enum Option {
    IN("in"),
    BETWEEN("between"),
    EQUALS("="),
    NOT_EQUALS("!="),
    GREATER_EQUALS(">="),
    LESS_EQUALS("<=");

    private String option;

    Option(String option) {
      this.option = option;
    }

    public String getOption() {
      return option;
    }

    @Override
    public String toString() {
      return option;
    }
  }

  public static void main(String[] args) {
    Option option = Option.BETWEEN;
    System.out.println(option.getOption());
    System.out.println(Option.GREATER_EQUALS);


  }

}


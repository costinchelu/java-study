package dp_behavioral.state.withoutstatepattern;

class Fan {

    final static int OFF = 0;
    final static int LOW = 1;
    final static  int MEDIUM = 2;

    int state = OFF;

    public Fan() {
    }

    public void pullChain() {
        if (state == OFF) {
            System.out.println("Turning fan on to low.");
            state = LOW;
        } else if (state == LOW) {
            System.out.println("Turning fan on to medium.");
            state = MEDIUM;
        } else if (state == MEDIUM) {
            System.out.println("Turning the fan off");
            state = OFF;
        }
    }

    public String toString() {
        if (state == OFF) {
            return "Fan is off";
        } else if (state == LOW) {
            return "Fan is on";
        }else if (state == MEDIUM) {
            return "Fan is medium";
        }

        return "Invalid state.";
    }
}

public class TwoUserChatDemo {
    public static void main(String[] args) throws Exception {
        String[] aliceMessages = {
            "Hey man, how’s it going?",

            "Same here. Long day. Traffic was brutal 😩",

            "Ugh. Weekends can’t come fast enough.",

            "Might hit the gym, then maybe catch a movie. You?",

            "Which one? The heist thing?",

            "LOL it’s “Money Heist.” Totally worth it though.",

            "For sure. Enjoy dinner. Later!"

        };

        String[] bobMessages = {
            "Yo! All good. Just got home from work. You?",

            "Tell me about it. Took me 45 minutes just to get out of downtown.",

            "Facts. Got any plans for Saturday?",

            "Nice. I’m thinking of chilling at home. Been meaning to start that Netflix show everyone’s talking about.",

            "Yeah, that one. “Money Grab” or something? Not sure of the name 😂",

            "Bet. Alright man, gonna grab some food. Catch you later?"

        };

        ChatUser u1 = new ChatUser("Alice", aliceMessages);
        ChatUser u2 = new ChatUser("Bob", bobMessages);

        u1.setPriority(Thread.MAX_PRIORITY);
        u2.setPriority(Thread.MIN_PRIORITY);

        u1.start();
        u2.start();

        Thread.sleep(1000);
        u2.pauseChat();
        System.out.println("Bob paused...");

        Thread.sleep(1000);
        u2.resumeChat();
        System.out.println("Bob resumed...");

        Thread.sleep(1000);
        u1.stopChat();
        System.out.println("Alice stopped...");

        u1.join();
        u2.join();

        System.out.println("Chat ended.");
    }
}

class ChatUser extends Thread {

    private volatile boolean paused = false;
    private volatile boolean running = true;
    private String[] messages;

    ChatUser(String name, String[] messages) {
        super(name);
        this.messages = messages;
    }

    public void run() {
        int i = 0;

        while (running && i < messages.length) {
            if (paused) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println(getName() + " was interrupted while paused.");
                }
                continue;
            }

            System.out.println(getName() + ": " + messages[i]);
            i++;

            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                System.out.println(getName() + " was interrupted.");
            }
        }

        System.out.println(getName() + " finished chatting.");
    }

    public void pauseChat() {
        paused = true;
    }

    public void resumeChat() {
        paused = false;
    }

    public void stopChat() {
        running = false;
    }
}

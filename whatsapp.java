// User class
class User {
    private String userId;
    private String username;
    private String password;
    // Other attributes and methods

    public User(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    // Other user-related methods
}

// Message class
class Message {
    private String messageId;
    private User sender;
    private List<User> receivers;
    private String content;
    private LocalDateTime timestamp;
    private boolean isEncrypted;
    private boolean isDelivered;
    private boolean isRead;
    // Other attributes and methods

    public Message(String messageId, User sender, List<User> receivers, String content) {
        this.messageId = messageId;
        this.sender = sender;
        this.receivers = receivers;
        this.content = content;
        this.timestamp = LocalDateTime.now();
        this.isEncrypted = false;
        this.isDelivered = false;
        this.isRead = false;
    }

    // Getters and setters
    // Methods for marking message as delivered and read
}

// MessageManager class
class MessageManager {
    private List<Message> sentMessages;
    private List<Message> receivedMessages;
    // Other attributes and methods

    public MessageManager() {
        this.sentMessages = new ArrayList<>();
        this.receivedMessages = new ArrayList<>();
    }

    public void sendMessage(Message message) {
        // Send the message and add it to the sent messages
    }

    public void receiveMessage(Message message) {
        receivedMessages.add(message);
        // Notify observers about new received message
    }

    // Other message management methods
}

// MessageSearchStrategy interface (Strategy)
interface MessageSearchStrategy {
    List<Message> searchMessages(List<Message> messages, String keyword);
}

// KeywordSearchStrategy class (Strategy)
class KeywordSearchStrategy implements MessageSearchStrategy {
    @Override
    public List<Message> searchMessages(List<Message> messages, String keyword) {
        // Implement keyword-based message search
    }
}

// SenderSearchStrategy class (Strategy)
class SenderSearchStrategy implements MessageSearchStrategy {
    @Override
    public List<Message> searchMessages(List<Message> messages, String sender) {
        // Implement sender-based message search
    }
}

// SearchManager class
class SearchManager {
    private MessageSearchStrategy searchStrategy;

    public void setSearchStrategy(MessageSearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Message> searchMessages(List<Message> messages, String keyword) {
        return searchStrategy.searchMessages(messages, keyword);
    }
}

// PresenceObserver interface
interface PresenceObserver {
    void onPresenceChange(User user, boolean online);
}

// PresenceManager class (Singleton)
class PresenceManager {
    private static PresenceManager instance;
    private Map<User, Boolean> presenceMap;
    private List<PresenceObserver> observers;

    private PresenceManager() {
        this.presenceMap = new HashMap<>();
        this.observers = new ArrayList<>();
    }

    public static synchronized PresenceManager getInstance() {
        if (instance == null) {
            instance = new PresenceManager();
        }
        return instance;
    }

    public void setPresence(User user, boolean online) {
        presenceMap.put(user, online);
        notifyObservers(user, online);
    }

    public void addObserver(PresenceObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PresenceObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(User user, boolean online) {
        for (PresenceObserver observer : observers) {
            observer.onPresenceChange(user, online);
        }
    }
}

// MessageEncryptionDecorator class (Decorator)
class MessageEncryptionDecorator extends Message {
    private Message message;

    public MessageEncryptionDecorator(Message message) {
        super(message.getMessageId(), message.getSender(), message.getReceivers(), message.getContent());
        this.message = message;
    }

    @Override
    public String getContent() {
        // Implement message encryption
    }

    @Override
    public boolean isEncrypted() {
        return true;
    }
}

// MessageState interface (State)
interface MessageState {
    void handleMessage(Message message);
}

// ComposingState class (State)
class ComposingState implements MessageState {
    @Override
    public void handleMessage(Message message) {
        // Handle message actions in composing state (e.g., saving drafts, editing)
    }
}

// SentState class (State)
class SentState implements MessageState {
    @Override
    public void handleMessage(Message message) {
        // Handle message actions in sent state (e.g., marking as sent, archiving)
    }
}

// ReceivedState class (State)
class ReceivedState implements MessageState {
    @Override
    public void handleMessage(Message message) {
        // Handle message actions in received state (e.g., marking as read, replying)
    }
}

// MessageStateContext class
class MessageStateContext {
    private MessageState currentState;

    public MessageStateContext() {
        this.currentState = new ComposingState();
    }

    public void setState(MessageState state) {
        this.currentState = state;
    }

    public void handleMessage(Message message) {
        currentState.handleMessage(message);
    }
}

// Main Class
public class WhatsAppMessenger {
    public static void main(String[] args) {
        // Create users
        User user1 = new User("user1", "john.doe", "password1");
        User user2 = new User("user2", "alice.smith", "password2");

        // Create message objects
        Message message1 = new Message("msg1", user1, List.of(user2), "Hi Alice, how are you?");
        Message message2 = new Message("msg2", user2, List.of(user1), "Hi John, I'm doing well.");

        // Create message manager
        MessageManager messageManager = new MessageManager();

        // Receive messages
        messageManager.receiveMessage(message1);
        messageManager.receiveMessage(message2);

        // Search messages
        SearchManager searchManager = new SearchManager();
        searchManager.setSearchStrategy(new KeywordSearchStrategy());
        List<Message> searchResults = searchManager.searchMessages(messageManager.getReceivedMessages(), "John");

        // Add message encryption using decorators
        Message encryptedMessage = new MessageEncryptionDecorator(message2);
        encryptedMessage.getContent(); // Returns the encrypted content
    }
}

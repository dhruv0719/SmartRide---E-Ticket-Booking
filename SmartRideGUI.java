import javax.swing.*;
import java.awt.*;
import model.Ticket;
import service.BookingService;
import service.RouteService;
import model.User;
import model.Route;
import service.TransportService;
import model.Transport;

public class SmartRideGUI {
    private JFrame frame;
    private JTextField nameField, phoneField, seatsField;
    private JComboBox<String> sourceBox, destinationBox, transportBox;
    private JTextArea ticketArea;
    private BookingService bookingService;

    public SmartRideGUI() {
        bookingService = new BookingService();
        frame = new JFrame("SmartRide - E-Ticket Booking");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Set background color
        frame.getContentPane().setBackground(new Color(240, 248, 255));
        
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(70, 130, 180));
        JLabel headerLabel = new JLabel("SmartRide E-Ticket Booking");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);

        // Form section
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Form fields
        JLabel nameLabel = createLabel("Name:");
        nameField = createTextField();

        JLabel phoneLabel = createLabel("Phone:");
        phoneField = createTextField();

        JLabel sourceLabel = createLabel("Source City:");
        String[] cities = {"Surat", "Mumbai", "Delhi", "Bengaluru", "Hyderabad"};
        sourceBox = createComboBox(cities);

        JLabel destLabel = createLabel("Destination City:");
        destinationBox = createComboBox(cities);

        JLabel transportLabel = createLabel("Transport:");
        String[] transports = {"Bus", "Train", "Flight"};
        transportBox = createComboBox(transports);

        JLabel seatsLabel = createLabel("Seats:");
        seatsField = createTextField();

        // Add form components
        addFormComponent(formPanel, nameLabel, nameField, gbc, 0);
        addFormComponent(formPanel, phoneLabel, phoneField, gbc, 1);
        addFormComponent(formPanel, sourceLabel, sourceBox, gbc, 2);
        addFormComponent(formPanel, destLabel, destinationBox, gbc, 3);
        addFormComponent(formPanel, transportLabel, transportBox, gbc, 4);
        addFormComponent(formPanel, seatsLabel, seatsField, gbc, 5);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255));
        JButton bookButton = new JButton("Book Ticket");
        bookButton.setFont(new Font("Arial", Font.BOLD, 16));
        bookButton.setBackground(new Color(70, 130, 180));
        bookButton.setForeground(Color.WHITE);
        bookButton.setFocusPainted(false);
        bookButton.setBorderPainted(false);
        bookButton.setPreferredSize(new Dimension(200, 40));
        bookButton.addActionListener(e -> bookTicket());
        buttonPanel.add(bookButton);

        // Center container (form + button)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(new Color(240, 248, 255));
        centerPanel.add(formPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(buttonPanel);

        // Ticket area
        JPanel ticketPanel = new JPanel(new BorderLayout());
        ticketPanel.setBackground(new Color(240, 248, 255));
        JLabel ticketLabel = new JLabel("Ticket Details");
        ticketLabel.setFont(new Font("Arial", Font.BOLD, 18));
        ticketLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ticketArea = new JTextArea(10, 40);
        ticketArea.setEditable(false);
        ticketArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ticketArea.setBackground(new Color(250, 250, 250));
        ticketArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        JScrollPane scrollPane = new JScrollPane(ticketArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ticketPanel.add(ticketLabel, BorderLayout.NORTH);
        ticketPanel.add(scrollPane, BorderLayout.CENTER);

        // Assemble panels
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(ticketPanel, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JTextField createTextField() {
        JTextField field = new JTextField(20);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        return field;
    }

    private JComboBox<String> createComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        return comboBox;
    }

    private void addFormComponent(JPanel panel, JLabel label, JComponent field,
                                  GridBagConstraints gbc, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        panel.add(field, gbc);
    }

    private void bookTicket() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String source = (String) sourceBox.getSelectedItem();
        String destination = (String) destinationBox.getSelectedItem();
        String transport = (String) transportBox.getSelectedItem();

        if (name.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Name and phone number are required.");
            return;
        }
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(frame, "Enter a valid 10-digit phone number.");
            return;
        }

        int seats;
        try {
            seats = Integer.parseInt(seatsField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid seat number.");
            return;
        }

        if (source.equals(destination)) {
            JOptionPane.showMessageDialog(frame, "Source and destination cannot be the same.");
            return;
        }

        // Create required objects
        User user = new User(name, phone);
        Route route = new RouteService().findRoute(source, destination);
        Transport transportObj = new TransportService().getTransport(transport);

        if (route == null) {
            JOptionPane.showMessageDialog(frame, "Route not available.");
            return;
        }
        if (transportObj == null) {
            JOptionPane.showMessageDialog(frame, "Transport not available.");
            return;
        }

        Ticket ticket = bookingService.bookTicket(user, route, transportObj, seats);
        if (ticket != null) {
            ticketArea.setText(ticket.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SmartRideGUI();
        });
    }
}

package com.app.config;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainData extends JFrame {
    private JPanel contentPane;
    private JPanel Container;
    private JPanel Dashboard;
    private JPanel SideBar;
    private JPanel Logo;
    private JPanel Nav;
    private JButton dashboardButton;
    private JButton transaksiButton;
    private JButton manageMenuButton;
    private JTabbedPane tabbedPane;
    private final DefaultListModel<String> minumanModel;
    private final DefaultListModel<String> makananModel;

    public MainData() {
        this.setSize(1020, 800);
        this.setMinimumSize(new Dimension(1020, 800));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        contentPane = new JPanel(new GridLayout(1, 4));

        minumanModel = new DefaultListModel<>();
        makananModel = new DefaultListModel<>();
        JList<String> listMinuman = new JList<>(minumanModel);
        JList<String> listMakanan = new JList<>(makananModel);

        contentPane.add(new JScrollPane(listMinuman));
        contentPane.add(new JScrollPane(listMakanan));
        setContentPane(contentPane);

        fetchDataMinuman();
        fetchDataMakanan();

    }

    private void fetchDataMinuman() {
        minumanModel.clear();
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM minuman";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String price = resultSet.getString("price");
                    minumanModel.addElement(name + " " + price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }
    }

    private void fetchDataMakanan() {
        makananModel.clear();
        try (Connection connection = DatabaseConfig.getConnection()) {
            String query = "SELECT * FROM makanan";
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String price = resultSet.getString("price");
                    makananModel.addElement(name + " " + price);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainData minum = new MainData();
                minum.setVisible(true);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
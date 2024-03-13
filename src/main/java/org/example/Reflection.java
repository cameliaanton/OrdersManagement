package org.example;
import java.lang.reflect.Field;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.util.List;

/**
 * A utility class that provides methods for working with reflection and JTable.
 */
public class Reflection {
        /**
        * Creates a JTable from a list of objects by extracting data and column names through reflection.
        *
        * @param objects The list of objects to populate the table with.
        * @return The created JTable.
        */
        public static JTable createTable(List<?> objects) {
            Object[][] data = extractData(objects);
            String[] columnNames = extractColumnNames(objects);

            JTable table = new JTable(data, columnNames);
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            table.getTableHeader().setReorderingAllowed(false);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            return table;
        }
        /**
        * Refreshes an existing JTable with new data and column names from a list of objects using reflection.
        *
        * @param table The JTable to refresh.
        * @param objects The list of objects to populate the table with.
        * @return The refreshed JTable.
        */
        public static JTable refreshTable(JTable table, List<?> objects) {
        if (objects.isEmpty()) {
            return table;
        }
        Class<?> classs = objects.get(0).getClass();
        Field[] fields = classs.getDeclaredFields();
        String[] columnNames = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }

        Object[][] data = extractData(objects);

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table.setModel(model);

        return table;
    }
        /**
        * Extracts the data from a list of objects using reflection.
        *
        * @param objects The list of objects.
        * @return The extracted data as a 2D array.
        */
        private static Object[][] extractData(List<?> objects) {
            Object[][] data = new Object[objects.size()][];

            for (int i = 0; i < objects.size(); i++) {
                Object obj = objects.get(i);
                Class<?> classs = obj.getClass();
                Field[] fields = classs.getDeclaredFields();
                Object[] rowData = new Object[fields.length];

                for (int j = 0; j < fields.length; j++) {
                    Field field = fields[j];
                    field.setAccessible(true);

                    try {
                        Object value = field.get(obj);
                        rowData[j] = value;
                    } catch (IllegalAccessException e) {
                        rowData[j] = "N/A";
                    }
                }

                data[i] = rowData;
            }

            return data;
        }
        /**
        * Extracts the column names from a list of objects using reflection.
         *
        * @param objects The list of objects.
        * @return The extracted column names as an array of strings.
        */
        private static String[] extractColumnNames(List<?> objects) {
            if (objects.isEmpty()) {
                return new String[0];
            }

            Class<?> classs = objects.get(0).getClass();
            Field[] fields = classs.getDeclaredFields();
            String[] columnNames = new String[fields.length];

            for (int i = 0; i < fields.length; i++) {
                columnNames[i] = fields[i].getName();
            }

            return columnNames;
        }
    }


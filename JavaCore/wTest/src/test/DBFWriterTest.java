package test;

import com.linuxense.javadbf.*;
import java.io.*;

public class DBFWriterTest {

  public static void main( String args[])
  throws DBFException, IOException {

    // let us create field definitions first
    // we will go for 3 fields
    //
    DBFField fields[] = new DBFField[4];

    fields[0] = new DBFField();
    fields[0].setName( "emp_code");
    fields[0].setDataType( DBFField.FIELD_TYPE_C);
    fields[0].setFieldLength( 10);

    fields[1] = new DBFField();
    fields[1].setName( "emp_name");
    fields[1].setDataType( DBFField.FIELD_TYPE_C);
    fields[1].setFieldLength( 20);

    fields[2] = new DBFField();
    fields[2].setName("salary");
    fields[2].setDataType( DBFField.FIELD_TYPE_N);
    fields[2].setFieldLength(12);
    fields[2].setDecimalCount(2);

    fields[3] = new DBFField();
    fields[3].setName( "ert");
    fields[3].setDataType(DBFField.FIELD_TYPE_N);
    fields[3].setFieldLength(12);
    fields[3].setDecimalCount(0);
    
    DBFWriter writer = new DBFWriter(new File(args[0]));
   // writer.setFields( fields);

    // now populate DBFWriter
    //

    Object rowData[] = new Object[4];
    rowData[0] = "1000";
    rowData[1] = "John";
    rowData[2] = new Double(45.00);
    rowData[3] = new Double(45.00);

    writer.addRecord( rowData);

    rowData = new Object[4];
    rowData[0] = "1001";
    rowData[1] = "Lalit";
    rowData[2] = new Double(45.70);
    rowData[3] = new Double(45.00);

    writer.addRecord( rowData);

    rowData = new Object[4];
    rowData[0] = "1002";
    rowData[1] = "Rohit";
    rowData[2] = new Double( 45.55);
    rowData[3] = new Double(45.00);

    writer.addRecord(rowData);

    //FileOutputStream fos = new FileOutputStream(args[0]);
    writer.write();
    //fos.close();
  }
}
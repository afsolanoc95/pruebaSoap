package com.programa.beans;



import java.util.List;


import org.apache.camel.BeanInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.programa.InputSOATest;
import com.programa.OutputSOATest;





import java.sql.Connection;
import java.sql.Statement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;


public class CamelProcessor implements Processor {
	private static final Logger LOG = Logger.getLogger("RegisterEmpleadoLog");
	  @BeanInject("dataSource")
	  private DataSource dataSource;
	  private String sqlinsert;
	  
    public void process(Exchange exchange) throws Exception {
        OutputSOATest out = new OutputSOATest();
        List soaList = exchange.getIn().getBody(List.class);
        InputSOATest inputSOATest = (InputSOATest) soaList.get(0);
        this.sqlinsert="INSERT INTO Empleado(nombres,apellidos, tipo_documento, numero_documento, fecha_nacimiento, fecha_vinculacion, cargo, salario,edad, tiempo_empresa) VALUE('names','lastna', 'typedoc','numberdoc','birthdate','datvin','status','salary','age','time')";
        
        this.sqlinsert = this.sqlinsert.replaceAll("names", inputSOATest.getNombres().toString());
        this.sqlinsert = this.sqlinsert.replaceAll("lastna", inputSOATest.getApellidos().toString());
        this.sqlinsert = this.sqlinsert.replaceAll("typedoc", inputSOATest.getTipoDocumento().toString());
        this.sqlinsert = this.sqlinsert.replaceAll("numberdoc", inputSOATest.getNumeroDocumento().toString());    
        this.sqlinsert = this.sqlinsert.replaceAll("birthdate", inputSOATest.getFechaNacimiento().toString());
        this.sqlinsert = this.sqlinsert.replaceAll("datvin", inputSOATest.getFechaVinculacion().toString());
        this.sqlinsert = this.sqlinsert.replaceAll("status", inputSOATest.getCargo().toString());
        this.sqlinsert = this.sqlinsert.replaceAll("salary", Double.toString(inputSOATest.getSalario()));
        this.sqlinsert = this.sqlinsert.replaceAll("age", inputSOATest.getEdad().toString());
        this.sqlinsert = this.sqlinsert.replaceAll("time", inputSOATest.getTiempoEmpresa().toString());
       

        conexion();

        exchange.getOut().setBody(out);
    }
    public void conexion()
    	    throws Exception
    	  {
    	    Connection connection = null;
    	    Statement statement =null;
    	    ResultSet resultSet = null;
    	    String ID=null;
    	    try
    	    {
    	      connection = getConnection();
    	      statement  =  connection.createStatement();
    	      statement.executeUpdate(this.sqlinsert);  
    	      LOG.debug("empleado registrado ");
    	    }
    	    finally
    	    {
    	      closeCon(connection, statement, resultSet);
    	    }
    	  }
    	  
    	  private int cont(ResultSet rs) throws SQLException {
    		  int i = 0;
    		  while(rs.next()) {
    		      i++;
    		  } 
    		  return i;
    	  }
    	  
    	  private ResultSet executeSQL(Connection cn, PreparedStatement ps)
    	    throws Exception
    	  {
    	    ResultSet rs = null;
    	    try
    	    {
    	      if (LOG.isDebugEnabled()) {
    	        LOG.debug("Ejecutando Consulta: ");
    	      }
    	      rs = ps.executeQuery();
    	    }
    	    catch (Exception e)
    	    {
    	      LOG.error("Se presento un error al ejecutar la consulta: ", e);
    	      throw e;
    	    }
    	    return rs;
    	  }
    	  
    	  private Connection getConnection()
    	    throws Exception
    	  {
    	    Connection conn = null;
    	    try
    	    {
    	      conn = this.dataSource.getConnection();
    	    }
    	    catch (SQLException e)
    	    {
    	      LOG.error("Se presento un error al obtener la Conexion con: " + this.dataSource, e);
    	      throw e;
    	    }
    	    return conn;
    	  }
    	  
    	  public void closeCon(Connection cn, Statement ps, ResultSet rs)
    	  {
    	    if (LOG.isDebugEnabled()) {
    	      LOG.debug("Closing connection: " + this.dataSource);
    	    }
    	    if (rs != null) {
    	      try
    	      {
    	        rs.close();
    	      }
    	      catch (SQLException e)
    	      {
    	        LOG.error("Error al cerrar la set de resultados. ", e);
    	      }
    	    }
    	    if (ps != null) {
    	      try
    	      {
    	        ps.close();
    	      }
    	      catch (SQLException e)
    	      {
    	        LOG.error("Error al cerrar la consulta. ", e);
    	      }
    	    }
    	    if (cn != null) {
    	      try
    	      {
    	        cn.close();
    	      }
    	      catch (SQLException e)
    	      {
    	        LOG.error("Error al cerrar la conexion. ", e);
    	      }
    	    }
    	  }
}
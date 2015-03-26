package sage;

import java.util.*;
import java.io.*;

public class sage_analysis_info implements Serializable{
  public int count_info_of_all;
  public Calendar start_time;
  public String analysis_name;

  public boolean create_input_folder=false;
  public boolean create_output_folder=false;
  public boolean create_error_folder = false;

  public boolean create_para_file_node=false;
  public boolean create_family_file_node=false;
  public boolean create_locus_file_node=false;
  public boolean create_trait_file_node=false;
  public boolean create_genome_file_node=false;
  public boolean create_type_file_node=false;
  public boolean create_output_file_path=false;

  public String para_file_path= new String();
  public String family_file_path= new String();
  public String locus_file_path= new String();
  public String trait_file_path= new String();
  public String genome_file_path= new String();
  public String ibd_file_path= new String();
  public String type_file_path= new String();

  public Vector input_file= new Vector();

  public sage_analysis_info(String name) {
    analysis_name = name;
  }

  public Calendar get_start_time()
  {
    return this.start_time;
  }

  int get_count_info_of_all() {
    return this.count_info_of_all;
  }

  String get_name() {
    return this.analysis_name;
  }

  String get_para_path() {
    return this.para_file_path;
  }

  String get_family_path() {
    return this.family_file_path;
  }

  String get_locus_path() {
    return this.locus_file_path;
  }

  String get_trait_path() {
    return this.locus_file_path;
  }

  String get_genome_path() {
    return this.genome_file_path;
  }

  String get_ibd_path() {
    return this.ibd_file_path;
  }

  String get_type_path() {
    return this.type_file_path;
  }

}

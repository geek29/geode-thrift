/**
 * The first thing to know about are types. The available types in Thrift are:
 *
 *  bool        Boolean, one byte
 *  byte        Signed byte
 *  i16         Signed 16-bit integer
 *  i32         Signed 32-bit integer
 *  i64         Signed 64-bit integer
 *  double      64-bit floating point value
 *  string      String
 *  binary      Blob (byte array)
 *  map<t1,t2>  Map from one type to another
 *  list<t1>    Ordered list of one type
 *  set<t1>     Set of unique elements of one type
 *
 * Did you also notice that Thrift supports C style comments?
 */
 
 namespace java com.github.geodethrift
 
struct Command { 
 	1: string command
 	2:map<string,string> env
}

exception CommandException {
  1: string whatCommand,
  2: string why
}

service GemfireManagement {
	string execute(1:Command cmd) throws (1:CommandException ex)
}
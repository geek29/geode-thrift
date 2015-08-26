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
 	2: map<string,string> env
}

exception CommandException {
  1: string whatCommand,
  2: string why
}

service GemfireManagement {
	string execute(1:Command cmd) throws (1:CommandException ex)
	
	string getHelp(1:string command)
}

const string INFO_RESULT = "info"
const string TABULAR_RESULT = "table"
const string COMPOSITE_RESULT = "composite"
const string ERROR_RESULT = "error"

typedef list<String> Row

struct InfoResult {
	1: list<string> messages
}

struct TableResult {
	1: list<string> colNames,
	2: i16 rows,
	3: i16 cols,
	4: list<Row> data
}

struct Section {
	1: string header,
	2: list<Section> sections,
	3: map<string,string> sectionMap,
	4: list<TableResult> tables
}

struct CompositeResult {
	1: list<string> sectionNames,
	2: string header,
	3: list<Section> sections
}

struct ErrorResult {
	1: list<string> messages
	2: i16 errorCode
}

struct ResultContent {
	1: string header,
	2: string footer,
	3: string type,
	4: optional InfoResult infoResult,
	5: optional TableResult tableResult,
	6: optional CompositeResult compositeResult,
	7: optional ErrorResult errorResult
}

struct CommandResult {
	1: string contentType,
	2: i16 status,
	3: string version,
	4: string sender,
	5: string debugInfo,
	6: string when,
	7: ResultContent content
}

namespace java com.github.geek29.geodethrift.management.commandResult

const string INFO_RESULT = "info"
const string TABULAR_RESULT = "table"
const string COMPOSITE_RESULT = "composite"
const string ERROR_RESULT = "error"

typedef list<string> Row

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
	/*2: list<Section> sections,*/
	3: map<string,string> sectionMap,
	4: list<TableResult> tables
}

struct CompositeResult {
	1: list<string> sectionNames,
	2: string header,
	3: list<Section> sections
}

struct ErrorResult {
	1: list<string> messages,
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
	6: string _when,
	7: ResultContent content
}

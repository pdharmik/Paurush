// TODO: translate to Japanese, modify appropriately from footerDateFormat to startDay
var text = new Array();

text['today'] = '\u4eca\u65e5';
text['time'] = '\u6642\u9593';

text['dayNamesShort'] = new Array(
		'\u65e5',
		'\u6708',
		'\u706b',
		'\u6c34',
		'\u6728',
		'\u91d1',
		'\u571f'
);
text['dayNames'] = new Array(
		'\u65e5\u66dc\u65e5',
		'\u6708\u66dc\u65e5',
		'\u706b\u66dc\u65e5',
		'\u6c34\u66dc\u65e5',
		'\u6728\u66dc\u65e5',
		'\u91d1\u66dc\u65e5',
		'\u571f\u66dc\u65e5'
);

text['monthNamesShort'] = new Array(
		'1\u6708',
		'2\u6708',
		'3\u6708',
		'4\u6708',
		'5\u6708',
		'6\u6708',
		'7\u6708',
		'8\u6708',
		'9\u6708',
		'10\u6708',
		'11\u6708',
		'12\u6708'
);

text['monthNames'] = new Array(
		'1\u6708',
		'2\u6708',
		'3\u6708',
		'4\u6708',
		'5\u6708',
		'6\u6708',
		'7\u6708',
		'8\u6708',
		'9\u6708',
		'10\u6708',
		'11\u6708',
		'12\u6708'
);

//%n = month; %j = day; %Y = year
text['footerDateFormat'] = '%Y/%n/%j',
text['dateFormat'] = '%Y/%n/%j',
text['footerDefaultText'] = '\u65e5\u4ed8\u3092\u9078\u629e',

text['clear'] = '\u30af\u30ea\u30a2\u65e5',
text['prev_year'] = '\u524d\u5e74',
text['prev_month'] = '\u524d\u6708',
text['next_month'] = '\u6765\u6708',
text['next_year'] = '\u6765\u5e74',
text['close'] = '\u30af\u30ed\u30fc\u30ba',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "\u30b9\u30bf\u30fc\u30c8\u4ed8\u304d %s";
text['startDay'] = 0;


RichCalendar.rc_lang_data['ja'] = text;

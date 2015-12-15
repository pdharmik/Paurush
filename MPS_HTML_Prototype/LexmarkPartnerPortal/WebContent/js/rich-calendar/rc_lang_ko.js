// TODO: translate to Korea, modify appropriately from footerDateFormat to startDay
var text = new Array();

text['today'] = 'Today_ko';
text['time'] = 'Time';

text['dayNamesShort'] = new Array(
		'\uc77c',
		'\uc6d4',
		'\ud654',
		'\uc218',
		'\ubaa9',
		'\uae08',
		'\ud1a0'
);
text['dayNames'] = new Array(
		'\uc77c',
		'\uc6d4',
		'\ud654',
		'\uc218',
		'\ubaa9',
		'\uae08',
		'\ud1a0'
);

text['monthNamesShort'] = new Array(
		'1\uc6d4(JAN)',
		'2\uc6d4(FEB)',
		'3\uc6d4(MAR)',
		'4\uc6d4(APR)',
		'5\uc6d4(MAY)',
		'6\uc6d4(JUN)',
		'7\uc6d4(JUL)',
		'8\uc6d4(AUG)',
		'9\uc6d4(SEP)',
		'10\uc6d4(OCT)',
		'11\uc6d4(NOV)',
		'12\uc6d4(DEC)'
);

text['monthNames'] = new Array(
		'1\uc6d4(JAN)',
		'2\uc6d4(FEB)',
		'3\uc6d4(MAR)',
		'4\uc6d4(APR)',
		'5\uc6d4(MAY)',
		'6\uc6d4(JUN)',
		'7\uc6d4(JUL)',
		'8\uc6d4(AUG)',
		'9\uc6d4(SEP)',
		'10\uc6d4(OCT)',
		'11\uc6d4(NOV)',
		'12\uc6d4(DEC)'
);

//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%Y.%m.%d',
text['dateFormat'] = '%Y.%m.%d',
text['footerDefaultText'] = 'Select date',

text['clear'] = 'Clear Date',
text['prev_year'] = 'Previous year',
text['prev_month'] = 'Previous month',
text['next_month'] = 'Next month',
text['next_year'] = 'Next year',
text['close'] = 'Close',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Start with %s";
text['startDay'] = 0;

//select and cancel
text['select'] = '\uc120\ud0dd';
text['cancel'] = '\ucde8\uc18c';

RichCalendar.rc_lang_data['ko'] = text;

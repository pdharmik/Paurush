/*
	Contributed by Carlos Nunes, cmanunes@gmail.com, 26.01.2008
*/

var text = new Array();

text['today'] = 'Idag';
text['time'] = 'Hora';

text['dayNamesShort'] = new Array(
'Søn',
'Man',
'Tir',
'Ons',
'Tor',
'Fre',
'Lør'
);
text['dayNames'] = new Array(
'Søndag',
'Mandag',
'Tirsdag',
'Onsdag',
'Torsdag',
'Fredag',
'Lørdag'
);

text['monthNamesShort'] = new Array(
'Jan',
'Feb',
'Mar',
'Apr',
'Maj',
'Jun',
'Jul',
'Aug',
'Sep',
'Okt',
'Nov',
'Dec'
);

text['monthNames'] = new Array(
'Januar',
'Februar',
'Marts',
'April',
'Maj',
'Juni',
'Juli',
'August',
'September',
'Oktober',
'November',
'December'
);

//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%d-%m-%Y',
text['dateFormat'] = '%d-%m-%Y',
text['footerDefaultText'] = 'Select date Danish',

text['clear'] = 'Limpar Data',
text['prev_year'] = 'Ano anterior',
text['prev_month'] = 'Mês anterior',
text['next_month'] = 'Próximo mês',
text['next_year'] = 'Próximo ano',
text['close'] = 'Luk',
text['startDay'] = '0',


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "Começa com %s";

//select and cancel
text['select'] = "Select";
text['cancel'] = "Cancel";

RichCalendar.rc_lang_data['da'] = text;

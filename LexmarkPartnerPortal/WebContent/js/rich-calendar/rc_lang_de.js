/*
	Contributed by Yves Vrod, yv@auvillage.net, 06.04.2009
*/

var text = new Array();

text['today'] = 'Heute';
text['time'] = 'Heure';

text['dayNamesShort'] = new Array(
'Son',
'Mon',
'Die',
'Mit',
'Don',
'Fre',
'Sam'
);
text['dayNames'] = new Array(
'Sonntag',
'Montag',
'Dienstag',
'Mittwoch',
'Donnerstag',
'Freitag',
'Samstag'
);

text['monthNamesShort'] = new Array(
'Jan',
'Feb',
'Mar',
'Apr',
'Mai',
'Jun',
'Jul',
'Aug',
'Sep',
'Okt',
'Nov',
'Dez'
);

text['monthNames'] = new Array(
'Januar',
'Februar',
'März',
'April',
'Mai',
'Juni',
'Juli',
'August',
'September',
'Oktober',
'November',
'Dezember'
);


//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%d.%m.%Y',
text['dateFormat'] = '%d.%m.%Y',
text['footerDefaultText'] = 'Wählen Sie das Datum',

text['clear'] = 'Annuler',
text['prev_year'] = 'Année précedente',
text['prev_month'] = 'Mois précédent',
text['next_month'] = 'Mois prochain',
text['next_year'] = 'Année prochaine',
text['close'] = 'schließen', 


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "1<sup>er</sup> jour %s";
text['startDay'] = 1;

//select and cancel
text['select'] = "Auswählen";
text['cancel'] = "Abbrechen";

RichCalendar.rc_lang_data['de'] = text;
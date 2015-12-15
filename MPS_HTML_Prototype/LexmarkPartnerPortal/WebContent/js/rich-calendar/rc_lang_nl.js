/*
	Contributed by Yves Vrod, yv@auvillage.net, 06.04.2009
*/

var text = new Array();

text['today'] = 'Vandaag';
text['time'] = 'Heure';

text['dayNamesShort'] = new Array(
'zon',
'maa',
'din',
'woe',
'don',
'vri',
'zat'
);
text['dayNames'] = new Array(
'zondag',
'maandag',
'dinsdag',
'woensdag',
'donderdag',
'vrijdag',
'zaterdag'
);

text['monthNamesShort'] = new Array(
'jan',
'feb',
'maa',
'apr',
'mei',
'jun',
'jul',
'aug',
'sep',
'okt',
'nov',
'dec'
);

text['monthNames'] = new Array(
'januari',
'februari',
'maart',
'april',
'mei',
'juni',
'juli',
'augustus',
'september',
'oktober',
'november',
'december'
);


//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%d/%m/%Y',
text['dateFormat'] = '%d/%m/%Y',
text['footerDefaultText'] = 'Pick a date Dutch',

text['clear'] = 'Annuler',
text['prev_year'] = 'Année précedente',
text['prev_month'] = 'Mois précédent',
text['next_month'] = 'Mois prochain',
text['next_year'] = 'Année prochaine',
text['close'] = 'Sluiten', 


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "1<sup>er</sup> jour %s";
text['startDay'] = 1;

//select and cancel
text['select'] = "Select";
text['cancel'] = "Cancel";

RichCalendar.rc_lang_data['nl'] = text;
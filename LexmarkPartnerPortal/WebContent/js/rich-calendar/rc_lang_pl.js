/*
	Contributed by Yves Vrod, yv@auvillage.net, 06.04.2009
*/

var text = new Array();

text['today'] = 'Dzis';
text['time'] = 'Heure';

text['dayNamesShort'] = new Array(
'Nie',
'Pon',
'Wto',
'Sro',
'Czw',
'Pia',
'Sob'
);
text['dayNames'] = new Array(
'Niedziela',
'Poniedzialek',
'Wtorek',
'Sroda',
'Czwartek',
'Piatek',
'Sobota'
);

text['monthNamesShort'] = new Array(
'Sty',
'Lut',
'Mar',
'Kwi',
'Maj',
'Cze',
'Lip',
'Sie',
'Wrz',
'Paz',
'Lis',
'Gru'
);

text['monthNames'] = new Array(
'Styczen',
'Luty',
'Marzec',
'Kwiecien',
'Maj',
'Czerwiec',
'Lipiec',
'Sierpien',
'Wrzesien',
'Pazdziernik',
'Listopad',
'Grudzien'
);


//%m = month; %d = day; %Y = year
text['footerDateFormat'] = '%Y-%m-%d',
text['dateFormat'] = '%Y-%m-%d',
text['footerDefaultText'] = 'Pick a date Polish',

text['clear'] = 'Annuler',
text['prev_year'] = 'Année précedente',
text['prev_month'] = 'Mois précédent',
text['next_month'] = 'Mois prochain',
text['next_year'] = 'Année prochaine',
text['close'] = 'Zamknij', 


// weekend days (0 - sunday, ... 6 - saturday)
text['weekend'] = "0,6";
text['make_first'] = "1<sup>er</sup> jour %s";
text['startDay'] = 1;

//select and cancel
text['select'] = "Select";
text['cancel'] = "Cancel";

RichCalendar.rc_lang_data['pl'] = text;
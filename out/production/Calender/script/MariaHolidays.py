from operator import le
import holidays
import pandas as pd 

df = [pd.DataFrame() for x in range(0,16)]
laender = [ "BB", "BE", "BW", "BY", "HB", "HE", "HH", "MV", "NI", "NW", "RP", "SH", "SL", "SN", "ST", "TH"]
//TO DO change name beft
beft = ["Neujahr","Karfreitag","Christi Himmelfahrt","Pfingstmontag","Erster Mai","Tag der Deutschen Einheit","Ostermontag","Erster Weihnachtstag","Zweiter Weihnachtstag"]
for index, land in enumerate(laender):
    LAND = holidays.country_holidays("DE",subdiv=land,years=[year for year in range(2010,2100,1)])
    df[index]["datum"],  df[index]["feiertag"], df[index]["LAND"]  = list(LAND.keys()),  list(LAND.values()), land

DF = pd.concat(df)
DF = DF[~DF['feiertag'].isin(beft)]

DF.to_csv("länder.csv")
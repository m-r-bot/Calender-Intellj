# GENERATES A .SQL script to insert all official German national holydays from 2019 to 2060  
# requirements: 
#   zureml==0.2.7
#   pandas==1.4.1

import pandas as pd
from azureml.opendatasets import PublicHolidays
from datetime import datetime

START_DATE =datetime(year=2010,month=1,day=1)
END_DATE = datetime(year=2040,month=12,day=31)
COUNTRY = 'DE'

df = pd.DataFrame()

feiertage_de = PublicHolidays(country_or_region=COUNTRY,start_date=START_DATE, end_date=END_DATE)
df_raw = feiertage_de.to_pandas_dataframe()

df["datum"] = df_raw["date"].dt.strftime('%Y-%m-%d')
df["name"] = df_raw["holidayName"]
df.to_csv("feiertage.csv",index=False)




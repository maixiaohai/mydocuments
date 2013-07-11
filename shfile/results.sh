#!/bin/sh
startDate=$(date -d 20120821 +%s)
endDate=$(date -d 20130628 +%s)
while [ $startDate -le $endDate ]
  do
		iced ./tool/index.coffee stats diff $(date -d @$startDate +%F) desc.diff.visits html >> results.html
		startDate=$(($startDate+86400))
	done
#/bin/sh

for file in $( find -maxdepth 1 -type d )
do

  if [ "$file" != "." ] 
  then
    echo "processing $file"
    if [ ! -e "$file/conf" ]
    then
      mkdir "$file/conf"
    fi

    svn propset svn:externals -F svn-externals.txt "$file/conf"
    cp config/conf/ant/project-targets.xml "$file/conf/."
    cp config/conf/ivy/ivysettings.xml "$file/conf/."
    cp config/conf/docbook/ivy.xml "$file/conf/docbook-ivy.xml"
  fi

done

#/bin/sh

for file in $( find -maxdepth 1 -type d )
do

  -- not very elegant, but it works...

  if [ "$file" != "." ] ; then
    if [ "$file" != "./.hg" ] ; then
      if [ "$file" != "./config" ] ; then
        echo "processing $file"
        if [ ! -e "$file/conf" ] ; then
          mkdir "$file/conf"
        fi

        cp config/conf/ant/project-targets.xml "$file/conf/."
        cp config/conf/ivy/ivysettings.xml "$file/conf/."
        cp config/conf/docbook/ivy.xml "$file/conf/docbook-ivy.xml"
      fi
    fi
  fi

done

import bs4 as bs
import os


indexes = []

def load_html(path):
    return bs.BeautifulSoup(open(path).read(),features="html.parser")

for root, subdirectories, files in os.walk('./'):
    for file in files:
        if file == 'index.html':
            if './docs/javadoc' != str(root):
                indexes.append(os.path.join(root,file))

for index in indexes:
    print("------------ " + index)
    soup = []
    with open(index) as inf:
        txt = inf.read()
        soup = bs.BeautifulSoup(txt,features="html.parser")
    soup.find('header').replace_with(load_html('./snippets/header.html'))
    with open(index,"w") as outf:
        outf.write(soup.prettify())
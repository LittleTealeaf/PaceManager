import bs4 as bs
import os


indexes = []

def load_html(path):
    return bs.BeautifulSoup(open(path).read(),features="html.parser")

for root, subdirectories, files in os.walk('./'):
    for file in files:
        if file == 'index.html':
            if './docs/javadoc' != str(root):
                indexes.append((root,file))

for index in indexes:
    path = os.path.join(index[0],index[1])
    soup = []
    with open(path) as inf:
        txt = inf.read()
        soup = bs.BeautifulSoup(txt,features="html.parser")
    soup.find('header').replace_with(load_html('./snippets/header.html'))
    soup.find('body').replace_with(load_html(os.path.join(index[0],'content.html')))
    with open(path,"w") as outf:
        outf.write(soup.prettify())
    

import styles from '../styles/Home.module.css'
import Link from 'next/link'

export default function Home() {
  return (
    <div>
      Hey, This page is currently under progress, so... Here's a link to the: <br />
      <Link href='/javadoc/index.html' passHref><a className='to-blue-500'>Javadocs</a></Link>
    </div>
  )
}

import styles from './page.module.css'
import Link from 'next/link'

export default function Home() {
  return (
    <main className={styles.main}>
      <Link href="/VoteManage">메뉴바 보러 가기</Link>
    </main>
  )
}

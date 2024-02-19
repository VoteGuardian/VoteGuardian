import { PC } from "@/hooks/useResize";
import Image from "next/image";
import './MainHeader.scss'
import Link from "next/link";

export default function MainHeader() {
    return (
        <>
            <PC>
                <div className="main-header-wrap">
                    <Image src="/icon.jpg" width={100} height={100} alt="icon"/>
                    {/* 로그인 페이지가 아직 없기 때문에 일단 로그인 후 메인 페이지로 이동 */}
                    <Link href="/VoteMain">로그인</Link>
                </div>
            </PC>
        </>
    )
}
'use client'
import { useEffect, useState } from 'react';
import './MenuBar.scss';
import Link from 'next/link'
import Image from "next/image";

export default function MenuBar() {
    const [voteView, setVoteView] = useState(false);
    const [settingView, setSettingView] = useState(false);
    const [width, setWidth] = useState(window.innerWidth);

    //투표(글자) 메뉴 클릭시 드롭다운
    function handleVoteDropDown() {
        setVoteView(!voteView);
    }

    //설정 메뉴 클릭시 드롭다운
    function handleSetDropDown() {
        setSettingView(!settingView);
    }

    function handleResize() {
        setWidth(window.innerWidth);
    }

    useEffect(() => {
        window.addEventListener("resize", handleResize);
        return() => {
            window.removeEventListener("resize", handleResize);
        }
    }, []);
    
    return(
        <div className="bar-wrap">
            <div className="header">투표 가디언</div>
            <div className="drop-menu">
                {/* onClick 이벤트로 클릭하면 {} 안의 함수가 실행됨  */}
                <p onClick={handleVoteDropDown}>투표</p>
                {/* voteView가 true일 때 표시 */}
                {voteView &&
                    <div className="drop-vote-menu">
                        <Link href="">투표 만들기</Link>
                        <Link href="">투표 참여하기</Link>
                        <Link href="">투표 관리하기</Link>
                    </div>
                }
            </div>
            <div className="drop-menu">
                <p onClick={handleSetDropDown}>설정</p>
                {settingView &&
                    <div className="drop-setting-menu">
                        <Link href="">마이페이지</Link>
                        <Link href="">로그아웃</Link>
                    </div>
                }
            </div>
            <div className="logo-wrap">
                <Image src="/icon.jpg" width={100} height={50}></Image>
            </div>
        </div>
    )
}
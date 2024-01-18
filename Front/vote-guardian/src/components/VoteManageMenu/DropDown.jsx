'use client'
import { useState } from 'react';
import Link from 'next/link'

export default function DropDown({title, type}) {
    const [voteView, setVoteView] = useState(false);
    const [settingView, setSettingView] = useState(false);

    //투표(글자) 메뉴 클릭시 드롭다운
    function handleVoteDropDown() {
        setVoteView(!voteView);
        console.log(voteView)
    }

    //설정 메뉴 클릭시 드롭다운
    function handleSettingDropDown() {
        setSettingView(!settingView);
    }

    return(
        <div className="drop-wrap">
            {type === 'voteView' ? 
            <p onClick={handleVoteDropDown}>{title}</p>
            : <p onClick={handleSettingDropDown}>{title}</p>}
            {type === 'voteView' ? voteView : settingView
                
            }
            {/* {voteView &&
                <div className="drop-menu">
                    <Link href="">투표 만들기</Link>
                    <Link href="">투표 참여하기</Link>
                    <Link href="">투표 관리하기</Link>
                </div>
            } */}
        </div>
    )
}
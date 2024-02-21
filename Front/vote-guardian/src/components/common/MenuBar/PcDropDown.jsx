'use client'
import { useState } from 'react';
import Link from 'next/link';
import './PcDropDown.scss';

export default function DropDown() {
    const [voteView, setVoteView] = useState(false);
    const [settingView, setSettingView] = useState(false);

    //투표(글자) 메뉴 클릭시 드롭다운
    function handleVoteDropDown() {
        setVoteView(!voteView);
    }

    //설정 메뉴 클릭시 드롭다운
    function handleSettingDropDown() {
        setSettingView(!settingView);
    }

    return(
        <>
            <div className='drop-wrap'>
                <div className={`drop-title ${voteView}`}>
                    <p onClick={handleVoteDropDown}>투표</p>
                </div>
                {voteView && 
                <div className='drop-menu'>
                    <Link href="/VoteCreate/Select">투표 만들기</Link>
                    <Link href={{pathname: '/VoteManage', query:{type: 'participate'}}}>투표 참여하기</Link>
                    <Link href={{pathname: '/VoteManage', query:{type: 'create'}}}>투표 관리하기</Link>
                </div>
                }
            </div>
            <div className='drop-wrap'>
                <div className={`drop-title ${settingView}`}>
                    <p onClick={handleSettingDropDown}>설정</p>
                </div>
                {settingView &&
                <div className='setting-menu'>
                    <Link href="">마이페이지</Link>
                    <Link href="">로그아웃</Link>
                </div>
                }
            </div>
        </>
    )
}
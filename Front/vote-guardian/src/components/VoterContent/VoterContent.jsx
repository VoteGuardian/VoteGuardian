import { useState } from 'react';
import './VoterContent.scss'
import Button from '../Common/Button/Button';
import { useRecoilState } from 'recoil';
import { voterListState } from '@/recoil/atoms/voteAtoms';
import Alert from '../Common/Alert/Alert';

export default function VoterContent() {
    const [voterOneFlag, setVoterOneFlag] = useState(false);
    const [voterFileFlag, setVoterFileFlag] = useState(false);
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [voterList, setVoterList] = useRecoilState(voterListState);
    //알람창
    const [alert, setAlert] = useState(false);
    const [alertText, setAlertText] = useState('');
    //투표자 검색
    function handleVoterSearch() {
        console.log('검색')
    }
    //투표자 한 명 등록 버튼 클릭
    function handleVoterOne() {
        setVoterOneFlag(true);
    }
    //투표자 한 명 이름
    function handleVoterOneName(e) {
        if(e.target.value.length > 30) {

        }
        else setName(e.target.value);
    }
    //투표자 한 명 이메일
    function handleVoterOneEmail(e) {
        //이메일 조건 확인
        setEmail(e.target.value);
    }
    //투표자 한 명 등록
    function handleVoterOneAdd() {
        //이름과 이메일 모두 작성되어야 함
        let voterIs = false;
        if(name !== '' && email !== '') {
            if(voterList.length > 0) {
                for(let i = 0; i < voterList.length; i++) {
                    //이미 등록된 사람이라면
                    if(voterList[i].email === email) {
                        setVoterOneFlag(false)
                        voterIs = true
                        setAlertText('이미 등록된 사람입니다')
                        handleAlert();
                    }
                }
            }
            if(!voterIs) {
                setVoterList(
                    prevList => [...prevList, {
                        name: name,
                        email: email
                    }]
                )
                setVoterOneFlag(false)
            }
        }
        else {
            setAlertText('이름이나 이메일을 다시 한 번 확인해주세요')
            handleAlert();
        }
    }
    
    //투표자 여러명 등록
    function handleVoterFileAdd() {
        setVoterFileFlag(true);
    }
    //투표자 등록 취소
    function handleVoterCancle() {
        if(voterOneFlag) setVoterOneFlag(false)
        else if(voterFileFlag) setVoterFileFlag(false)
    }
    //알람창
    function handleAlert() {
        //나타났다가 2초 뒤 쯤 사라지게 함
        setAlert(true)
        setTimeout(() => {
            setAlert(false)
        }, 2000)
    }
    
    return(
        <>
        {alert &&
                <Alert type='negative' text={alertText}/>
        }
        {voterOneFlag &&
            <div className='voter-one-add-wrap'>
                <p>투표자 한 명 등록하기</p>
                <div className='vote-one-wrap'>
                    <label for="voterName">이름</label>
                    <input onInput={handleVoterOneName} type="text" id="voterName" placeholder='이름을 작성해주세요'></input>
                </div>
                <div className='vote-one-wrap'>
                    <label for="voterName">이메일</label>
                    <input onInput={handleVoterOneEmail} type="text" id="voterName" placeholder='이메일을 작성해주세요'></input>
                </div>
                <div className="button-group-flex">
                    <div onClick={handleVoterCancle}>
                        <Button buttonType='select negative' text='취소'></Button>
                    </div>
                    <div onClick={handleVoterOneAdd}>
                        <Button buttonType='select positive' text='등록'></Button>
                    </div>
                </div>
            </div>
        }
            <div className='voter-content-flex'>
                <div className='voter-content-wrap'>
                    <div className='voter-title'>
                        <p>투표자 등록</p>
                    </div>
                    <div className='voter-search-wrap'>
                        <input type='text'></input>
                        <div className='voter-search-button'onClick={handleVoterSearch}>
                            <p>검색</p>
                        </div>
                    </div>
                    <div className='voter-list-big-wrap'>
                        <div className='voter-list-top'>
                            <div className='voter-list-button-group'>
                                <div onClick={handleVoterOne}>
                                    <p>한 명 등록하기</p>
                                </div>
                                <div onClick={handleVoterFileAdd}>
                                    <p>파일로 등록하기</p>
                                </div>
                            </div>
                            <div className='voter-num-group'>
                                <p>전체 250</p>
                                <p>선택 250</p>
                            </div>
                        </div>
                        <div className='voter-list-content'>

                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
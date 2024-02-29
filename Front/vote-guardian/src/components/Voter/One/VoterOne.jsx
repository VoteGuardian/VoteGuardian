import Alert from "@/components/Common/Alert/Alert";
import Button from "@/components/Common/Button/Button";
import { voterListState, voterOneState } from "@/recoil/atoms/voteAtoms";
import { useState } from "react";
import { useRecoilState } from "recoil";

export default function VoterOne() {
    const [voterOne, setVoterOne] = useRecoilState(voterOneState);
    const [voterList, setVoterList] = useRecoilState(voterListState);
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');

    //알람창
    const [alert, setAlert] = useState(false);
    const [alertText, setAlertText] = useState('');

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
                        setVoterOne(false)
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
                setVoterOne(false)
            }
        }
        else {
            setAlertText('이름이나 이메일을 다시 한 번 확인해주세요')
            handleAlert();
        }
    }

    //알람창
    function handleAlert() {
        //나타났다가 2초 뒤 쯤 사라지게 함
        setAlert(true)
        setTimeout(() => {
            setAlert(false)
        }, 2000)
    }

    //투표자 등록 취소
    function handleVoterCancle() {
        setVoterOne(false)
    }

    return (
        <>
        {alert &&
                <Alert type='negative' text={alertText}/>
        }
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
        </>
    )
}